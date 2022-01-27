package se.liu.tetris.tetris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class HighscoreList
{
    private List<Highscore> highscores;

    public HighscoreList() {
	highscores = new ArrayList<>();
    }

    public void addScore(Highscore playerScore) throws IOException{
        try {
	    readJsonFile();
	} catch(UncheckedIOException e){
	    highscores = new ArrayList<>();
            e.printStackTrace();
	}
	highscores.add(playerScore);
	highscores.sort(new ScoreComparator());
	saveJsonFile();
    }
    public void readJsonFile() throws UncheckedIOException {
        String fileString = System.getProperty("user.dir") + File.separator + "Data.json";
        File input = new File(fileString);
        Gson gson = new Gson();
        try (FileReader fileInput = new FileReader(input)) {
	    JsonElement fileElem = JsonParser.parseReader(fileInput);
	    JsonArray elemAsJsonArray = fileElem.getAsJsonArray();
	    List<Highscore> highscoreList = new ArrayList<>();
	    for(JsonElement score: elemAsJsonArray){
		Highscore personScore = gson.fromJson(score, Highscore.class);
		highscoreList.add(personScore);
	    }
	    highscores = highscoreList;
        } catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public void saveJsonFile() throws IOException{
	String fileString = System.getProperty("user.dir") + File.separator + "tempData.json";
	File newSave = new File(fileString);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	FileWriter jsonWriter = new FileWriter(fileString);
	gson.toJson(highscores, jsonWriter);
	jsonWriter.close();
	if (newSave.exists()) {
	    String oldSave = System.getProperty("user.dir") + File.separator + "Data.json";
	    File oldFile = new File(oldSave);
	    if(oldFile.delete()){
	        if(!newSave.renameTo(oldFile)){
	            throw new IOException("Couldnt change name of temporary saveFile");
		}
	    }
	    else{
		throw new IOException("Couldnt delete old saveFile");
	    }
	}
	else{
	     throw new IOException("Couldnt save new highscoreList to temporary file");
	}
    }

    public static void main(String[] args) {
        HighscoreList lista = new HighscoreList();
    }

    public Highscore getHighScore(int index){
        return highscores.get(index);
    }
    public int getLength(){
        final int maxHighscoresDisplayed = 10;
	return Math.min(highscores.size(), maxHighscoresDisplayed);
    }

}
