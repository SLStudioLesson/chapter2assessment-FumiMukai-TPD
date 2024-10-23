package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        System.out.println("\nRecipes:");
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        System.out.println("\nEnter recipe name: ");
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        System.out.println("\nEnter search query (e.g., 'name=Tomato&ingredient=Garlic'): ");
                        searchRecipe();
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        // readRecipesで作ったリストをこちらでもリストにする
        ArrayList<String> recipes = fileHandler.readRecipes();
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            // リストの範囲で繰り返し
            for (String recipe : recipes) {

                /**
                 * 一つ目の「,」とその後ろを切り離したい → データ.split(" 記号 ",いくつに分割するか)
                 * └この場会、データは1行分のほうのrecipeで、記号は","、2個（料理と材料）に分割
                 * String型の配列「parts」に代入する=パーツ1に料理、2に材料が入る
                 */
                String[] parts = recipe.split(",", 2);
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + parts[0]);
                System.out.println("Main Ingredients: " + parts[1]);
            }

            // 閉じるためのハイフン
            System.out.println("-----------------------------------");
        }

    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */

    private void addNewRecipe() throws IOException {
        // レシピ名を入力
        String recipeName = reader.readLine();
        // 材料を入力
        System.out.println("Enter main ingredients (comma separated): ");
        String ingredients = reader.readLine();

        fileHandler.addRecipe(recipeName, ingredients);
        System.out.println("Recipe added successfully.");
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {
        // 検索クエリを入力させる
        String query = reader.readLine();

        fileHandler.searchRecipe(query);

        System.out.println("\nSearch Results:" + "_______________"); // 結果を表示

        // 一致レシピなし
        System.out.println("No recipes found matching the criteria.");
    }

}