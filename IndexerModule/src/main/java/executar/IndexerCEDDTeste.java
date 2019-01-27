package executar;

import indexers.Indexer;

import java.io.IOException;

public class IndexerCEDDTeste {
    public static void main(String[] args){
                Indexer indexer = new Indexer();
        try {
            indexer.indexerCEDD("C:\\Users\\Maria\\Documents\\NetBeansProjects\\SearcherModule\\web\\resources\\imagens\\");

        }catch (IOException e){

            System.out.println(e.getMessage());
        }
    }
}
