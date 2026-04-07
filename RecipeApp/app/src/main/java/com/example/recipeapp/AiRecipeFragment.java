package com.example.recipeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass that integrates with the OpenAI API.
 * This fragment allows users to enter ingredients and prompts an AI to generate a recipe.
 */
public class AiRecipeFragment extends Fragment {

    private TextInputEditText etIngredients;
    private Button btnGenerateRecipe;
    private TextView tvRecipeResult;
    private ProgressBar progressBar;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public AiRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ai_recipe, container, false);

        etIngredients = root.findViewById(R.id.etIngredients);
        btnGenerateRecipe = root.findViewById(R.id.btnGenerateRecipe);
        tvRecipeResult = root.findViewById(R.id.tvRecipeResult);
        progressBar = root.findViewById(R.id.progressBar);

        btnGenerateRecipe.setOnClickListener(v -> {
            String ingredients = etIngredients.getText().toString().trim();
            if (ingredients.isEmpty()) {
                Toast.makeText(getContext(), "Please enter ingredients", Toast.LENGTH_SHORT).show();
            } else {
                fetchRecipeFromAI(ingredients);
            }
        });

        return root;
    }

    /**
     * Executes the network request in a background thread to fetch a recipe from OpenAI API.
     * @param ingredients The ingredients provided by the user.
     */
    private void fetchRecipeFromAI(String ingredients) {
        progressBar.setVisibility(View.VISIBLE);
        tvRecipeResult.setText("Thinking...");
        btnGenerateRecipe.setEnabled(false);

        executorService.execute(() -> {
            String resultText;
            try {
                URL url = new URL("https://api.openai.com/v1/chat/completions");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                
                // Read the API Key securely from BuildConfig
                String apiKey = BuildConfig.OPENAI_API_KEY;
                if (apiKey == null || apiKey.isEmpty()) {
                    apiKey = "YOUR_OPENAI_API_KEY"; // Fallback, though user should replace this
                }
                connection.setRequestProperty("Authorization", "Bearer " + apiKey);
                connection.setDoOutput(true);

                // Build JSON Payload manually
                String promptText = "Give me a detailed recipe using the following ingredients: " + ingredients;
                
                JSONObject messageObj = new JSONObject();
                messageObj.put("role", "user");
                messageObj.put("content", promptText);
                
                JSONArray messagesArray = new JSONArray();
                messagesArray.put(messageObj);
                
                JSONObject rootObj = new JSONObject();
                rootObj.put("model", "gpt-3.5-turbo");
                rootObj.put("messages", messagesArray);
                rootObj.put("temperature", 0.7);

                try(OutputStream os = connection.getOutputStream()) {
                    byte[] input = rootObj.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);			
                }

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    
                    JSONObject responseJson = new JSONObject(response.toString());
                    resultText = responseJson.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
                } else {
                    resultText = "Error generating recipe. Please verify your OpenAI API Key in local.properties. Response code: " + responseCode;
                    Log.e("AiRecipeFragment", "OpenAI Error Response code: " + responseCode);
                }
            } catch (Exception e) {
                resultText = "Connection error failed: " + e.getMessage();
                Log.e("AiRecipeFragment", "Exception while fetching recipe", e);
            }

            final String finalResult = resultText;
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    if (isAdded()) {
                        progressBar.setVisibility(View.GONE);
                        tvRecipeResult.setText(finalResult);
                        btnGenerateRecipe.setEnabled(true);
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
