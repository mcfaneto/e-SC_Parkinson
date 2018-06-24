package escparkinson;


import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;

// classe para carregar a base de dados
// testa d=se a base está ok.

class LerBaseDados {
 
//inicializa o construtor

//LerBaseDados(){System.out.println("Estamos Lendo a Base de Dados ... \n");}
  
void imprimirBaseArff() throws Exception{
   
    //importa a base de dados ARFF utilizando classes da Ferramenta Weka
    DataSource source;
      source = new DataSource("UPDRS.arff");
    
      Instances Dados = source.getDataSet();
    //imprime informações associadas à base de dados
    System.out.println("Numero de instancias:" + Dados.numInstances());  
    System.out.println("Numero  de  atributos:" + Dados.numAttributes());  
    //imprime o conteúdo da base na tela  
    System.out.println("Base de Dados:");
    System.out.println(Dados.toString());
  }//fim imprimirBaseArff
}//fim Class
