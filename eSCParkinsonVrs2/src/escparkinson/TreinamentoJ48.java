package escparkinson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;


/*
    <repositories>
        <repository>
            <id>jboss-3rd-party-releases</id>
            <url>https://repository.jboss.org/nexus/content/repositories/thirdparty-releases/</url>
        </repository>
    </repositories>
    <dependencies>
        <!-- https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-dev -->
        <dependency>
            <groupId>nz.ac.waikato.cms.weka</groupId>
            <artifactId>weka-dev</artifactId>
            <version>3.7.5</version>
        </dependency>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>20.0-hal</version>
        </dependency>
    </dependencies>
 */
public class TreinamentoJ48 extends LerBaseDados {

    private Instances treinandoDados;

    public static void main(String[] args) throws Exception {
        
        /* Criacao  do Objeto da Classe de teste
        * Leitura da Base de Dados 
        * Aparecendo a leitura  está Ok
        */
        
        LerBaseDados objLerBaseDados = new LerBaseDados();
        objLerBaseDados.imprimirBaseArff();
        //testar se a base está sendo lida
        
           
        //aqui é o corpo do  treinamento com J48 - Similar ao C45.
        // Vale a pena depois tentar com RandomTree, RandoForest,  RepTREE e RNA (Multilayer Perceptron)
        
        try {
            System.out.println("\n \n "
                    + "Aqui iniciou o Treinamento ... \n"
                    + "Árvore de Decisão \n"
                    + "J48 (C45)\n \n"
                    + "Resultados...\n");
            TreinamentoJ48 arvoreDecisaoJ48 = new TreinamentoJ48("UPDRS.arff");
            J48 tree = arvoreDecisaoJ48.performTraining();
            System.out.println(tree.toString());
            
            Instance testInstance = arvoreDecisaoJ48.
                    getTestInstance("Leather", "yes", "historical");
            int result = (int) tree.classifyInstance(testInstance);
            String resultados = arvoreDecisaoJ48.treinandoDados.attribute(3).value(result);
            System.out.println("Test with: " + testInstance + "  Result: " + resultados);

            testInstance = arvoreDecisaoJ48.
                    getTestInstance("Paperback", "no", "historical");
            result = (int) tree.classifyInstance(testInstance);
            resultados = arvoreDecisaoJ48.treinandoDados.attribute(3).value(result);
            System.out.println("Testes com: " + testInstance + "  Resultados: " + resultados);
        } catch (Exception ex) {
        }
        
         /**
         Aqui comeca os testes com a RNA MLP
         */
        
        // Criando  o Objeto  e darei inicio  a RNA -  Treinamento
    
        
        MultiLayerPerceptron objMLP = new MultiLayerPerceptron();
        System.out.println("\n"
                + "Inicio o do Treinamento com  RNA - MLP"
                + "\n");
        
        objMLP.RNA();
            
    }

    
    
    public TreinamentoJ48(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            treinandoDados = new Instances(reader);
            treinandoDados.setClassIndex(treinandoDados.numAttributes() - 1);
        } catch (IOException ex) {
        }
    }

    private J48 performTraining() {
        J48 j48 = new J48();
        String[] opcoes = {"-U"};
      //Use unpruned tree. -U
        try {
            j48.setOptions(opcoes);
            j48.buildClassifier(treinandoDados);
        } catch (Exception ex) {
        }
        return j48;
    }

    private Instance getTestInstance(
            String outlook, String temperature, String humidity, String wind, String playTennis) {
        Instance instance = new DenseInstance(5);
        instance.setDataset(treinandoDados);
        instance.setValue(treinandoDados.attribute(0), outlook);
        instance.setValue(treinandoDados.attribute(1), temperature);
        instance.setValue(treinandoDados.attribute(2), humidity);
        instance.setValue(treinandoDados.attribute(3), wind);
        instance.setValue(treinandoDados.attribute(4), playTennis);
        return instance;
    }

    private Instance getTestInstance(String leather, String yes, String historical) {
        throw new UnsupportedOperationException("Sem Suporte,  ainda!"); 
//Para alterar o corpo dos métodos gerados, escolha Ferramentas | Modelos.
    }
    
}
//os testes,  realizei com os do esxercicios do Professor
//@attribute outlook {Sunny, Overcast, Rain} 
//@attribute temperature {Hot, Mild, Cool} 
//@attribute humidity {High, Normal}
//@attribute wind {Weak, Strong} 
//@attribute playTennis {Yes, No} 