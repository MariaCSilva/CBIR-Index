/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.ProdutoList;


/**
 *
 * @author Maria
 */
public class FileUtil {
     public List<Produto> lerJsonList(String path) throws FileNotFoundException{

         Gson gson = new Gson();         
           JsonReader jsonReader =  gson.newJsonReader(new FileReader(path));
         ProdutoList produtoList = gson.fromJson(jsonReader, ProdutoList.class);

         List<Produto> prods = produtoList.getProdutos();
        return prods;
    }
}
