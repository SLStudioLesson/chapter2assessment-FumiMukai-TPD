package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能 recipes.txtファイルからレシピデータを読み込み、コンソールに一覧表示
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */

    String filename = "recipes.txt";

    public ArrayList<String> readRecipes() {
        // リスト作る
        ArrayList<String> recipes = new ArrayList<>();
        // リストに情報を格納
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recipes.add(line);
            }
            return recipes;
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return null;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName  レシピ名
     * @param ingredients 材料名
     */
    //

    String recipeName;
    String ingredients;

    public void addRecipe(String recipeName, String ingredients) {
        // RecopeUIから追加メニューの情報を受け取ってrecipes.txtへ記録
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        String contentToWrite = recipeName + ", " + ingredients;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(contentToWrite);
            writer.newLine(); // 改行する
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file:" + e.getMessage());
        }
    }

    /**
     * 設問3
     * ユーザーが指定した条件（レシピ名や主な材料）に基づいてrecipes.txtファイル内のレシピを検索し、
     * 一致するものを表示する機能をステップに従い作成してください。
     *
     * @param query 検索クエリ
     */

    String query;

    public void searchRecipe(String query) {
        // クエリを受け取る
        this.query = query;
        // 検索はindexを使う？？？
    }
}
