package executar;

import indexers.Indexer;

import java.io.IOException;

public class IndexerFCTHTeste {
    public static void main(String[] args){
            Indexer indexer = new Indexer();
        try {
            indexer.indexerFCTH("C:\\Users\\Maria\\Documents\\NetBeansProjects\\SearcherModule\\web\\resources\\imagens\\");
        }catch (IOException e){

            System.out.println(e.getMessage());
        }
    }
}
