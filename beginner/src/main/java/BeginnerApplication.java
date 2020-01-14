import com.google.gson.GsonBuilder;
import sun.tools.tree.UplevelReference;

import java.util.ArrayList;

public class BeginnerApplication {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("First block", "0"));
        System.out.println("trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block", "0"));
        System.out.println("trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block", "0"));
        System.out.println("trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid" + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe blockchain: ");
        System.out.println(blockchainJson);

	}

	public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i > blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("previous hashes not equal");
                return false;
            }
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
