package test;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.*;

public class MLPClassifierLeaner {
    static String testData = "C:\\Users\\iStore\\IdeaProjects\\Matrix\\test.csv";
    static int rows = 0;
    static int inputs = 0;


    public static void main(String[] args) {
        inputs = getRowsFromDataSet(testData);
        System.out.println(rows + " " + inputs);
        try {
            main(rows, inputs, 2, testData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getRowsFromDataSet(String toFile) {
        try {
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(toFile));
            while ((line = reader.readLine()) != null){
                if(rows == 0){
                    String[] splitLine = line.split(",");
                    inputs = splitLine.length - 1;
                }
                rows++;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public static void main(int batchSize, int numInputs, int numOutputs, String dataForTrain)
            throws IOException, InterruptedException {

        int seed = 123;
        double learningRate = 0.1;
        int nEpochs = 250 ;
        int numHiddenNodes = 15;


        RecordReader rr = new CSVRecordReader();
        rr.initialize(new FileSplit(new File(dataForTrain)));
        DataSetIterator trainIter = new RecordReaderDataSetIterator(rr, batchSize, 0, numOutputs);
        DataSet allData = trainIter.next();
        allData.shuffle();

        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.7);

        DataSet trainingData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();

        //We need to normalize our data. We'll use NormalizeStandardize (which gives us mean 0, unit variance):
        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(trainingData);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
        normalizer.transform(trainingData);     //Apply normalization to the training data
        normalizer.transform(testData);         //Apply normalization to the test data. This is using statistics calculated from the *training* set




        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed) //include a random seed for reproducibility
                .activation(Activation.RELU)
                .weightInit(WeightInit.XAVIER)
                .updater(new Nesterovs(learningRate, 0.98))
                .l2(learningRate * 0.005) // regularize learning model
                .regularization(true)
                .list()
                .layer(0, new DenseLayer.Builder() //create the first input layer.
                        .nIn(numInputs)
                        .nOut(numHiddenNodes)
                        .build())
                .layer(1, new DenseLayer.Builder() //create the second input layer
                        .nIn(numHiddenNodes)
                        .nOut(numHiddenNodes + 30)
                        .build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD) //create hidden layer
                        .activation(Activation.SOFTMAX)
                        .nIn(numHiddenNodes + 30)
                        .nOut(numOutputs)
                        .build())
                .pretrain(false).backprop(true) //use backpropagation to adjust weights
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

        for (int n = 0; n < nEpochs; n++){
            model.fit(trainingData);
        }

        System.out.println("Evaluete model...");
        Evaluation evaluation = new Evaluation(numOutputs);

            INDArray featurs = testData.getFeatureMatrix();
            INDArray lables = testData.getLabels();
            INDArray predicted = model.output(featurs, false);
            evaluation.eval(lables, predicted);

        System.out.println(evaluation.stats());
    }
}