package indexers;


import model.Produto;
import net.semanticmetadata.lire.builders.GlobalDocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
import net.semanticmetadata.lire.utils.FileUtils;
import net.semanticmetadata.lire.utils.LuceneUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Indexer {
    
    public void imageIndex(String pathImagens, 
                           Class metodo, 
                           String pathIndex) 
        throws IOException {
        boolean passed = false;
        if(pathImagens.length() > 0){
            File f = new File(pathImagens);
            if(f.exists() && f.isDirectory()){
                passed = true;
            }
        }
        if(!passed){
            JOptionPane.showMessageDialog(null,"FAIL");
            System.exit(1);
        }
        ArrayList<String> images = FileUtils.
            getAllImages(new File(pathImagens),true);
        System.out.println(images.size());
        GlobalDocumentBuilder gdb = 
            new GlobalDocumentBuilder(metodo);
        System.out.println(gdb);
        IndexWriter iw = LuceneUtils.
                createIndexWriter(pathIndex,true,
                        LuceneUtils.AnalyzerType.WhitespaceAnalyzer);
        for(Iterator<String> it = images.iterator();it.hasNext();){
            String imageFilePath = it.next();
            try{
                BufferedImage img = ImageIO.
                    read(new FileInputStream(imageFilePath));
                Document document = gdb.
                    createDocument(img,imageFilePath);
                iw.addDocument(document);
                iw.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void textInfoIndex(Produto p, String caminhoIndex) 
        throws IOException {      
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = (Directory) FSDirectory.
            open(Paths.get(caminhoIndex));
        IndexWriterConfig config = 
            new IndexWriterConfig(analyzer);
        IndexWriter iwriter = 
            new IndexWriter(directory, config);
        Document doc = new Document();
        doc.add(new Field("nome", 
                p.getNome(), 
                TextField.TYPE_STORED));
        doc.add(new Field("caminho", 
                p.getImagemPath(), 
                TextField.TYPE_STORED));
        doc.add(new Field("preco", 
                p.getPreco(), 
                TextField.TYPE_STORED));
        doc.add(new Field("peca", 
                p.getCategoria().getPeca(), 
                TextField.TYPE_STORED));
        doc.add(new Field("estampa", 
                p.getCategoria().getTipoEstampa(), 
                TextField.TYPE_STORED));
        doc.add(new Field("loja", 
                p.getLoja(), 
                TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.commit();
        iwriter.close();
    }
    
    public void indexerCEDD(String path) throws IOException {
        String pathIndex = "C:\\Users\\Maria\\Documents\\NetBeansProjects\\SearcherModule\\web\\resources\\indexCEDD";
        imageIndex(path,CEDD.class,pathIndex);
    }
    public void indexerFCTH(String path) throws IOException {
        String pathIndex = "C:\\Users\\Maria\\Documents\\NetBeansProjects\\SearcherModule\\web\\resources\\indexFCTH";
        imageIndex(path,FCTH.class,pathIndex);
    }
    public void indexerInfo(Produto p) throws IOException {
        String pathIndex = "C:\\Users\\Maria\\Documents\\NetBeansProjects\\SearcherModule\\web\\resources\\indexInfo";
        textInfoIndex(p,pathIndex);
    }
}


