
import java.util.ArrayList;

import org.w3c.dom.Node;


public class DecisionTree {
	private TreeNode root = null; //stores the root of the decision tree
	
	
	public void train(ArrayList<Example> examples){
		int numFeatures = 0;
		if(examples.size()>0) //get the number of featuers in these examples
			numFeatures = examples.get(0).getNumFeatures();
		
		//initialize empty positive and negative lists
		ArrayList<Example> pos = new ArrayList<Example>();
		ArrayList<Example> neg = new ArrayList<Example>();
		
		//paritition examples into positive and negative ones
		for(Example e: examples){
			if (e.getLabel())
				pos.add(e);
			else
				neg.add(e);
		}
		
		//create the root node of the tree
		root = new TreeNode(null, pos, neg, numFeatures);
		
		//call recursive train()  on the root node
		train(root, numFeatures);
	}
	
	/**
	 * TODO: Complete this method
	 * The recursive train method that builds a tree at TreeNode node
	 * @param node: current node to train
	 * @param numFeatures: total number of features
	 */
	private void train(TreeNode node, int numFeatures){
		//base case 2
		if(node.pos.size() == 0 && node.neg.size() == 0) {//base case 2
			if(node.parent == null) {
				node.decision = false;
			}else {
				node.decision = node.parent.pos.size() > node.parent.neg.size(); 
			}
			node.isLeaf = true;
			return;
		}else if(node.pos.size() == 0) {//base case 1
			node.decision = false;
			node.isLeaf = true;
			return;
		}else if(node.neg.size() == 0) {
			node.decision = true;
			node.isLeaf = true;
			return;
		}
				
		//select a feature with the highest information gain that is min(getRemainingEntropy(f)) 0 < f < numFeatures and !used[f]
		int optimalFeatureId = -1;
		double minEntropy = Double.MAX_VALUE;
		
		for(int featureId = 0; featureId < numFeatures; featureId++) {
			if(node.featureUsed(featureId)) continue;
			double entropy = getRemainingEntropy(featureId, node);
			if(entropy < minEntropy) {
				minEntropy = entropy;
				optimalFeatureId = featureId;
			}
		}
		
		//no more attributes
		if(optimalFeatureId == -1) {//base case 3(all features are used)
			if(node.parent == null) {
				node.isLeaf = true;
				return;
			}
			node.decision = node.parent.pos.size() > node.parent.neg.size(); //base case 3
			node.isLeaf = true;
		}else {
			node.setSplitFeature(optimalFeatureId);
			createChildren(node, numFeatures);
			train(node, numFeatures);
		}
		
		return;
	}
	
	/**
	 * TODO: Complete this method
	 * Creates the true and false children of TreeNode node
	 * @param node: node at which to create children
	 * @param numFeatures: total number of features
	 */
	private void createChildren(TreeNode node, int numFeatures){
		ArrayList<Example> truePos = new ArrayList<>();
		ArrayList<Example> trueNeg = new ArrayList<>();
		ArrayList<Example> falsePos = new ArrayList<>();
		ArrayList<Example> falseNeg = new ArrayList<>();
		
		for(Example e : node.pos){
			if(e.getFeatureValue(node.getSplitFeature())) {//windy yes
				 if(e.getLabel()) {
					 truePos.add(e);
				 }else {
					 trueNeg.add(e);
				 }
			    
			}else {//windy no
				 if(e.getLabel()) {
					 falsePos.add(e);
				 }else {
					 falseNeg.add(e);
				 }
				 
			}
		}
		//windy ? true/neg  label? pos/neg
		node.trueChild = new TreeNode(node, truePos, trueNeg, numFeatures);
		node.falseChild = new TreeNode(node, falsePos, falseNeg, numFeatures);
	}
	
	
	/**
	 * TODO: Complete this method
	 * Computes and returns the remaining entropy if feature is chosen
	 * at node.
	 * @param feature: the feature number
	 * @param node: node at which to find remaining entropy
	 * @return remaining entropy at node
	 */
	private double getRemainingEntropy(int feature, TreeNode node){
		ArrayList<Example> p = new ArrayList<Example>();
		ArrayList<Example> n = new ArrayList<Example>();
		
		//These variables are counting the number of negative/positive example after splitting
		int posPos = 0;//positive example with feature positive
		int posNeg = 0;//positive example with feature negative
		int negPos = 0;//negative example with feature positive
		int negNeg = 0;//negative example with feature negative
		
		//Split the node by feature
		for(Example e : node.pos) {
			if(e.getFeatureValue(feature) == true) {
				p.add(e);
				posPos++;
			}
			else if(e.getFeatureValue(feature) == false) {
				n.add(e);
				posNeg++;
			}
		}
		for(Example e : node.neg) {
			if(e.getFeatureValue(feature) == true) {
				p.add(e);
				negPos++;
			}
			else if(e.getFeatureValue(feature) == false) {
				n.add(e);
				negNeg++;
			}
		}
		
		//Calculate the remaining entropy
		int tSize = p.size() + n.size();
		return getEntropy(posPos,negPos)*(p.size()/tSize) + getEntropy(posNeg,negNeg)*(n.size()/tSize);
		
		
		
	}
	
	/**
	 * TODO: complete this method
	 * Computes the entropy of a node given the number of positive and negative examples it has
	 * @param numPos: number of positive examples
	 * @param numNeg: number of negative examples
	 * @return - entropy
	 */
	private double getEntropy(int numPos, int numNeg){
		double pPos = numPos/(numPos + numNeg);
		double pNeg = numNeg/(numPos + numNeg);
		return -log2(pPos)*pPos - log2(pNeg)*pNeg;
	}
	
	/**	
	 * Computes log_2(d) (To be used by the getEntropy() method)
	 * @param d - value
	 * @return log_2(d)
	 */
	private double log2(double d){
		return Math.log(d)/Math.log(2);
	}
	
	/** 
	 * TODO: complete this method
	 * Classifies example e using the learned decision tree
	 * @param e: example
	 * @return true if e is predicted to be  positive,  false otherwise
	 */
	public boolean classify(Example e){
		return classify(e,root);
	}
	
	public boolean classify(Example e, TreeNode currnode) {
		if(currnode.isLeaf) {
			return currnode.decision;
		}
		if(e.getFeatureValue(currnode.getSplitFeature())){
			return classify(e,currnode.trueChild);
		}
		else {
			return classify(e,currnode.falseChild);
		}
		//return currnode.decision;
		//return false;
	}
	
	
	
	
	//----------DO NOT MODIFY CODE BELOW------------------
	public void print(){
		printTree(root, 0);
	}
	

	
	private void printTree(TreeNode node, int indent){
		if(node== null)
			return;
		if(node.isLeaf){
			if(node.decision)
				System.out.println("Positive");
  			else
				System.out.println("Negative");
		}
		else{
			System.out.println();
			doIndents(indent);
			System.out.print("Feature "+node.getSplitFeature() + " = True:" );
			printTree(node.trueChild, indent+1);
			doIndents(indent);
			System.out.print("Feature "+node.getSplitFeature() + " = False:" );//+  "( " + node.falseChild.pos.size() + ", " + node.falseChild.neg.size() + ")");
			printTree(node.falseChild, indent+1);
		}
	}
	
	private void doIndents(int indent){
		for(int i=0; i<indent; i++)
			System.out.print("\t");
	}
}
