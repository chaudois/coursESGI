import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by damie on 03/10/2017.
 */
public class JSONtoCSV {
    public static void main(String args[]) {

        JSONObject output;
        try {
            output = new JSONObject(readFile(args[0]));
            JSONArray docs = output.getJSONArray("infile");
            File file=new File(args[1]);
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String readFile(String url){
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        try{

            br=new BufferedReader(new FileReader(url));
            try {
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
