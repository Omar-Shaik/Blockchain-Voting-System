import java.security.MessageDigest;

public class Block{
	private String data;
	private String hash;
	private String prevHash;
	
	public Block(String data, String prevHash){
		this.data = data;
		this.setHash();
		this.prevHash = prevHash;
	}
	
	public String getData(){
		return this.data;
	}
	
	public String getHash(){
		return this.hash;
	}
	
	public String getPrevHash(){
		return this.prevHash;
	}
	
	public void setData(String data){
		this.data = data;
		setHash();
	}
	
	public void setHash(){
		try{
			MessageDigest d = MessageDigest.getInstance("SHA-256");
			d.update(this.data.getBytes());
			byte[] result = d.digest();
			StringBuffer s = new StringBuffer();
			for(byte b:result){
				s.append(Integer.toHexString(b & 0xff).toString());
			}
			this.hash = s.toString();
		}catch(Exception e){
			System.out.println("Error Occured");
			e.printStackTrace();
		}	
	}
}
