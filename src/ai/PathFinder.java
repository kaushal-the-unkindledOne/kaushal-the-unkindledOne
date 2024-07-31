package ai;

import java.util.ArrayList;

import main.GamePanel;

public class PathFinder {
	
	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	

	public PathFinder(GamePanel gp) {
		this.gp = gp;
		instantiateNode();
	}
	
	public void instantiateNode() {
		node = new Node[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			node[col][row] = new Node(col,row);
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void resetNode() {

		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			//RESET OPEN, CHECKED, SOLID
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
	   }
		//RESET OTHER SETTINGS	
			openList.clear();
			pathList.clear();
			goalReached = false;
			step = 0;
	}
	
	public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
		resetNode();
		
		//SET START AND GOAL NODE
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
        int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			//set solid nodes
			//check tiles
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			if(gp.tileM.tile[tileNum].collision == true) {
				node[col][row].solid = true;
			}
			//check interactive tiles
			for(int i = 0; i < gp.iTile[1].length; i++){
				if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible == true) {
					int itCol = gp.iTile[gp.currentMap][i].worldX/gp.tilesize;
					int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tilesize;
					node[itCol][itRow].solid = true;
					
					
				}
			}
			//CHECK COST
			getCost(node[col][row]);
			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
   }
	
	public void getCost(Node node) {
		
		//G COST
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		//H COST
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		//F COST
		node.fCost = node.gCost + node.hCost;
	}
	
	public boolean search() {
		while(goalReached == false && step < 500) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			//CHECK CURRENTNODE
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//OPEN UP NODDE
			if(row - 1 >= 0) {
				openNode(node[col][row-1]);
			}
			//OPEN LEFT NODDE
			if(col - 1 >= 0) {
				openNode(node[col-1][row]);
			}
			//OPEN DOWN NODDE
			if(row + 1 < gp.maxWorldRow) {
				openNode(node[col][row+1]);
			}
			//OPEN RIGHT NODDE
			if(col + 1 < gp.maxWorldCol) {
				openNode(node[col+1][row]);
			}
			
			//FIND THE BEST NODE
			int bestNodeIndex = 0;
			int bestNodeFcost = 999;
			
			for(int i = 0; i < openList.size(); i++) {
				if(openList.get(i).fCost < bestNodeFcost) {
					bestNodeIndex = i;
					bestNodeFcost = openList.get(i).fCost;
				}
				else if(openList.get(i).fCost == bestNodeFcost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			if(openList.size() == 0) {
				break;
			}
			currentNode = openList.get(bestNodeIndex);
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		return goalReached;
		
	}
	
	public void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackThePath() {
		
		Node current = goalNode;
		
		while(current != startNode) {
			pathList.add(0,current);
			current = current.parent;
		}	
	}

}
