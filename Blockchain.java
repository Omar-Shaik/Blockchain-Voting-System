import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Blockchain{
	private ArrayList<Block> chain;
	private int tail;
	private Date d;
	private BlockingQueue<Block> bq;
	//private int difficulty;
	
	public Blockchain(BlockingQueue<Block> bq){
		chain = new ArrayList<>();
		d = new Date(2020,1,1);
		Block genesis = new Block("Let the games begin", "0", Long.toString(d.getTime()));
		chain.add(genesis);
		tail = 0;
		this.bq = bq;
	}
	
	public int getLength(){
		return this.tail+1;
	}
	
	public String getBlock(int blockNum){
		return(chain.get(blockNum).getData());
	}
	
	public void printChain(){
		for(Block b:chain){
			System.out.print(b.getData());
		}
	}
	
	public void addBlockWithNonce(Block bl) throws InterruptedException{
		int work = proofOfWork(bl.getHash());
		bl.setNonce(work);
		System.out.println(work);
		chain.add(bl);
		bq.put(bl);
		this.tail++;
		verifyChain();
	}
	
	public void addBlockWithoutNonce(Block bl) throws InterruptedException, NoSuchAlgorithmException{
		if(verifyNonce(bl)){
			chain.add(bl);
			bq.put(bl);
			this.tail++;
			verifyChain();
		}
	}
	
	public void createBlock(String data) throws InterruptedException{
		String prev = this.chain.get(this.tail).getHash();
		Block b = new Block(data, prev, Long.toString(d.getTime()));
		addBlockWithNonce(b);
	}
	
	public boolean verifyNonce(Block bl) throws NoSuchAlgorithmException{
		boolean verified = false;
		MessageDigest d = MessageDigest.getInstance("SHA-256");
		String hashed = bl.getHash() + bl.getNonce();
		d.update(hashed.getBytes());
		byte[] result = d.digest();
		StringBuffer s = new StringBuffer();
		for(byte b:result){
			s.append(Integer.toHexString(b & 0xff).toString());
		}
		hashed = s.toString();
		if(hashed.substring(0,1).equals("0")){
			verified = true;
		}
		return verified;
	}
	
	public void verifyChain(){
		ArrayList<Block> tempChain = new ArrayList<Block>();
		for(int i = 1; i < chain.size();i++){
			//System.out.println(chain.get(i).getData());
			tempChain.add(chain.get(i));
			if(!chain.get(i).getPrevHash().equals(chain.get(i-1).getHash())){
				chain = tempChain;
			}
		}
	}
	
	public int proofOfWork(String h){
		int i = 0;
		try{
			MessageDigest d = MessageDigest.getInstance("SHA-256");
			while(true){
				String hashed = h + String.valueOf(i);
				d.update(hashed.getBytes());
				byte[] result = d.digest();
				StringBuffer s = new StringBuffer();
				for(byte b:result){
					s.append(Integer.toHexString(b & 0xff).toString());
				}
				hashed = s.toString();
				System.out.println(hashed);
				if(hashed.substring(0,2).equals("00")){
					break;
				}
				i++;
			}
		}catch(Exception e){
			System.out.println("Error Occured");
			e.printStackTrace();
		}
		return i;
	}
	/**
	public static void main(String args[]){
		Blockchain bc = new Blockchain();
		bc.addBlock("Hello");
	}
	**/
}