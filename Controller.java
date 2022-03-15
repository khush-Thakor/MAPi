package application;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;

public class Controller {

	@FXML
	private ImageView imageV;
	@FXML
	private DatePicker  dateInput;
	@FXML
	private TextArea imageInfo;
	@FXML
	private ListView<String> photoList;
	@FXML
	private TextField imgSrcText;
	@FXML
	private Button copyButton;
	
	ArrayList<Photo>photos = new ArrayList<Photo>();
	
	HttpURLConnection conn;
	
	HashMap<String, Integer> idToPhoto = new LinkedHashMap<String,Integer>();
	
	Stack<Photo> photoHistory = new Stack<Photo>();
	
	@FXML
	Hyperlink historyButton = new Hyperlink();
	
	int mode = 1;
	
	private int photoIdSelected;
	
	public void setImage() throws IOException{
		
	// Creation of the Date the User Inputs	
	LocalDate sDate = dateInput.getValue();
	
	// Formation of apiKey for the date entered
	String apiKey = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date="+sDate+
	"&api_key=Ri7l2K0C8IcYrKQRAtbYGekgYZekkJqS8G1ZMBkU";
	
	
	//URL Connection
	URL url = new URL(apiKey.toString());
	
	conn = (HttpURLConnection) url.openConnection();
	
	conn.setRequestMethod("GET");
	
	conn.connect();
	
	// Result returned from the API
	StringBuffer apiResult = new StringBuffer();
	
	Scanner scan = new Scanner(url.openStream());
	
	while (scan.hasNext()) {
		
		apiResult.append(scan.nextLine());
		
	}
	
	scan.close();
	
	//JSON String from the Result Returned
	
	String apiJSON = apiResult.toString();

	JSONObject apiReturn = new JSONObject(apiJSON);
	
	JSONArray apiPhotos = apiReturn.getJSONArray("photos");
	
	photos.clear();
	
	// Setting the Photos in the Photo List
	for (int i = 0;i<apiPhotos.length();i++) {
		
		JSONObject apiPhoto = (JSONObject)apiPhotos.get(i);
		
		Photo rPhoto = new Photo();
		
		rPhoto.setId(Integer.toString(apiPhoto.getInt("id")));
		
		rPhoto.setSol(Integer.toString(apiPhoto.getInt("sol")));
		
		JSONObject camera  = (JSONObject) apiPhoto.get("camera");
		
		rPhoto.setCameraId(Integer.toString(camera.getInt("id")));
		
		rPhoto.setRoverId(Integer.toString(camera.getInt("rover_id")));
		
		rPhoto.setCameraName(camera.getString("full_name"));
		
		rPhoto.setImgSrc(apiPhoto.getString("img_src"));
		
		rPhoto.setDate(apiPhoto.getString("earth_date"));
		
		JSONObject rover = (JSONObject) apiPhoto.get("rover");
		
		rPhoto.setRoverName(rover.getString("name"));
		
		rPhoto.setRoverStatus(rover.getString("status"));
		
		rPhoto.setLandD(rover.getString("landing_date"));
		
		rPhoto.setLaunchD(rover.getString("launch_date"));
		
		photos.add(rPhoto);		
	}
	
	// Change our Data Structure
	
	idToPhoto.clear();
	
	for (int i = 0;i<photos.size();i++) {
		
		idToPhoto.put(photos.get(i).getId(), i);
		
	}
	
	// Update the Image List
	photoList.getItems().clear();
	
	photoList.getItems().addAll(idToPhoto.keySet());
	
	conn.disconnect();
	
	}
	// Update the Image after selection
	public void imageSelect() throws IOException {
		
		copyButton.setText("Copy");
		
		photoIdSelected = photoList.getSelectionModel().getSelectedIndex();
		
		Image image = new Image(photos.get(photoIdSelected).getImgSrc());
		
		imageV.setImage(image);
		
		imageInfo.setText("Photo ID: "+photos.get(photoIdSelected).getId()+"\n"+
		"Sol: "+photos.get(photoIdSelected).getSol()+"\n"
		+"Camera ID: "+photos.get(photoIdSelected).getCameraId()+"\n"
		+"Camera Name: "+photos.get(photoIdSelected).getCameraName()+"\n"
		+"Rover ID: "+photos.get(photoIdSelected).getRoverId()+"\n"
		+"Date: "+photos.get(photoIdSelected).getDate()+"\n"
		+"Rover Name: "+photos.get(photoIdSelected).getRoverName()+"\n"
		+"Rover Landing Date"+photos.get(photoIdSelected).getLandD()+"\n"
		+"Rover Launching Date: "+photos.get(photoIdSelected).getLaunchD()+"\n"
		+"Rover Status: "+photos.get(photoIdSelected).getRoverStatus()+"\n"
		);
		
		imgSrcText.setText(photos.get(photoIdSelected).getImgSrc());
		
		if(mode>0)
		photoHistory.push(photos.get(photoIdSelected));
		
	}
	
	public void downloadImage() throws IOException {
        // Sets the URL
        URL url = new URL(photos.get(photoIdSelected).getImgSrc());

        // Open input stream to initiate transfer
        InputStream is = url.openStream();

        // Open output stream to determine destination
        OutputStream os = new FileOutputStream("Roverimage(" + photos.get(photoIdSelected).getImgSrc() + ").jpg");

        // setting length of while loop. 1048576 = 1MB.
        byte[] b = new byte[1048576];
        int length;

        // Writing directly into the output stream.
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        // Close both streams.
        is.close();
        os.close(); 
        
    }
	
	public void copyText() throws InterruptedException {
		
		Clipboard clip = Clipboard.getSystemClipboard();
		
		ClipboardContent cont = new ClipboardContent();
		
		cont.putString(photos.get(photoIdSelected).getImgSrc()); //Change ImgSrc to the proper variable or method to get the link
		
		clip.setContent(cont);
		
		copyButton.setText("Copied!");
		
	}

	public void historyButtonM() throws IOException {
		
		mode*=-1;
		
		historyButton.setVisited(false);
		
		if (mode>0) {
			
			photoList.getItems().clear();
			
			idToPhoto.clear();
			
			historyButton.setText("View History");
			
			setImage();
			
		}
		else {
			
		getHistory();
			
		}
		
	}
	
	public void getHistory() throws IOException {
		
		photos.clear();
		
		photoList.getItems().clear();
		
		idToPhoto.clear();
		
		Queue<Photo>auxS = new LinkedList<Photo>();
		
		int stackS = photoHistory.size();
		
		// Stack to Queue Twice to keep the same order of photoHistory (Stack)
		
		for (int i = 0;i<stackS;i++) {
			
			photos.add(photoHistory.peek());
			
			idToPhoto.put(photoHistory.peek().getId(), i);
			
			auxS.add(photoHistory.pop());
			
		}
		
		for (int i = 0;i<stackS;i++) {
			
			photoHistory.push(auxS.remove());
			
		}
		
		for (int i = 0;i<stackS;i++) {
			
			auxS.add(photoHistory.pop());;
			
		}
		
		for (int i = 0;i<stackS;i++) {
			
			photoHistory.push(auxS.remove());
			
		}
		
		
		for (int i = 0;i<photoHistory.size();i++) {
			
			idToPhoto.put(photoHistory.get(i).getId(), i);
			
		}
		
		photoList.getItems().addAll(idToPhoto.keySet());
		
		historyButton.setText("Photos");
			
		
	}
	
	
}
