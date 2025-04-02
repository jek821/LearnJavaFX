package todo.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import todo.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StorageService {
    private static final String FILE_PATH = "tasks.json";
    private static final Gson gson = new Gson();

    public static List<Task> loadTasks() {
        try (Reader reader = new FileReader(FILE_PATH)){
            Type taskListType = new TypeToken<List<Task>>(){}.getType();
            return gson.fromJson(reader, taskListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    public static void saveTasks(List<Task> tasks){
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}