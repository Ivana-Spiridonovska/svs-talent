package app;
public class Menu {

	private String content;
	private int numberOfOptions;
	
	public Menu(){
		
	}

	public Menu(String content, int nOptions) {
		this.content = content;
		this.numberOfOptions = nOptions;
	}

	public void addHeader(String s) {
		content = s + "\n";
	}

	public void addOption(String s) {
		numberOfOptions++;
		content += "  " + numberOfOptions + ". " + s + "\n";
	}

	public void addTail(String s) {
		content += s;

	}

	public int getExitChoice() {
		return numberOfOptions;
	}

	public void display() { 
        Reader.println(content);  
    }
	public String getUserSelection() {
		String selection = null;
		boolean valid = false;
		while (!valid) {
			selection = Reader.readString();
			if(isNumeric(selection)){
				if (Integer.parseInt(selection) >= 1
						&& Integer.parseInt(selection) <= numberOfOptions)
					valid = true;
				else {
					Reader.println("Invalid option!");
					Reader.println("Enter valid option:");
					valid = false;
				}
			}
			else{
				Reader.println("You have to enter number!");
				valid = false;
			}
			
		}

		if (!valid)
			return null;
		else
			return selection;

	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  

}
