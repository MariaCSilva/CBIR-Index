package executar;

import indexers.Indexer;
import model.Produto;
import util.FileUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LerJsonTeste {
    public static void main(String[] args){
        FileUtil fileUtil = new FileUtil();
        Indexer infoIndexer = new Indexer();
        List<Produto> produtos = null;
        try {
            produtos = fileUtil.lerJsonList("C:\\Users\\Maria\\Documents\\NetBeansProjects\\SearcherModule\\web\\resources\\info\\info.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Produto p: produtos) {
            System.out.println(p);
            try {
                infoIndexer.indexerInfo(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
