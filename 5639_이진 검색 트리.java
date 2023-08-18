import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BynarySearchTree tree = new BynarySearchTree();
		int cnt = 0;
		while(true) {
			try {
				int idx = Integer.parseInt(br.readLine());
				cnt++;
				tree.AddNode(idx);
			} catch (IOException e) {
				tree.postorderTree(tree.root, 0);
			}
		}
		
	}
}

class BynarySearchTree {
	static Node root = null; //클래스 할당 시 루트노드를 생성
	
	//특정 값 BST에 추가
	public static void AddNode(int value) {
		//BST가 빈 경우
		Node node = new Node(value); //매개변수로 받은 값에 대한 노드를 생성
		if(root == null) {
			root = node; //루트에 새로운 노드를 할당
		} else { //BST가 비어있지 않은 경우
			Node head = root; //루트를 가리키는 헤더
			Node CurrentNode; //현재를 가리키는 포인터
			
			while(true) {
				CurrentNode = head;
				
				if(head.Vertex > value) { //현재 루트보다 작은 경우
					head = head.leftNode;
					
					
					if(head == null) { //헤드가 널일 경우는 왼쪽 자식 노드가 없는 경우
						//현재 헤드위치에 새로운 노드 할당
						head = node;
						break;
					}
				} else { //현재 루트보다 클 경우
					head = head.rightNode; //현재 루트의 오른쪽 자식노드로 탐색
					
					
					if(head == null) {//헤드가 널일 경우는 오른쪽 자식 노드가 없는 경우
						head = node;
						break;
					}
				}
				
			}
		}
	}
	//특정 값을 가진 노드 삭제 메서드
	public static boolean Remove(int Value) {
		Node removeNode = root; //루트에서부터 삭제대상의 위치를 탐색
		Node parent_remover = null; //해당 노드를 찾아 삭제 후 삭제된 노드의 부모에 연결시켜주기 위한 변수 선언
		
		while(removeNode.Vertex != Value) { //루트노드가 찾는 값이 아니라면 ==> 탐색
			parent_remover = removeNode; //부모 노드를 루트 노드로 카피
			
			//삭제할 값이 현재 노드보다 작다면 왼쪽 탐색
			if(removeNode.Vertex > Value) {
				removeNode = removeNode.leftNode;
			} else {
				removeNode = removeNode.rightNode;
			}
			
			//대소관계를 비교해가며 탐색했을 때 leaf Node인 경우 삭제를 위한 탐색은 실패로 끝
			if(removeNode == null) {
				return false;
			}
		}
		
		//while을 빠져나왔다는건 삭제할 값의 노드를 BST에서 발견했을 경우 (즉 removeNode == 삭제대상노드)
		
		//삭제대상노드가 자식이 없을 경우 => 해당 노드만 삭제 후 종료
		if(removeNode.leftNode == null && removeNode.rightNode == null) {
			if(removeNode == root) { //삭제 대상이 트리의 루트일 때
				removeNode = null;
			} else if (removeNode == parent_remover.rightNode) { //삭제대상 노드가 부모의 오른쪽 노드일 때
				parent_remover.rightNode = null;
			} else { //삭제대상 노드가 부모의 오른쪽 노드일 때
				parent_remover.leftNode = null;
			}
		}
		
		//삭제대상노드의 왼쪽 자식노드만 존재할 경우 => parent_remover = removeNode.leftNode
		if(removeNode.leftNode != null && removeNode.rightNode == null) {
			if(removeNode == root) {//삭제할 노드가 최상단 루트 노드라면
				root = removeNode.leftNode;
			} else if(removeNode == parent_remover.rightNode) { //삭제할 노드가 부모의 오른쪽 노드라면
				parent_remover.rightNode = removeNode.leftNode;
			} else {
				parent_remover.leftNode = removeNode.leftNode;
			}
		}
		
		//삭제대상노드의 왼쪽 오른쪽 자식 모두 존재할 경우
		if(removeNode.leftNode != null && removeNode.rightNode != null) {
			//왼쪽 서브트리의 가장 큰 값 or 오른쪽 서브트리의 가장 작은 값으로 대체 (여기서는 전자 사용)
			Node ParentNode = removeNode; //대체노드의 부모노드
			
			//삭제 노드 왼쪽 서브 트리에서 탐색
			Node replaceNode = removeNode.leftNode;
			
			while(replaceNode.rightNode != null) { //오른쪽 자식이 없다면 왼쪽에서 가장 큰값
				ParentNode = replaceNode;
				replaceNode = replaceNode.rightNode;
			}
			
			if(replaceNode != removeNode.rightNode) {
				//가장 큰 값을 선택하기 때문에 대체 노드의 왼쪽 자식은 빈 노드가 되버리는 걸 처리
				ParentNode.rightNode = replaceNode.leftNode;
				
				replaceNode.leftNode = removeNode.leftNode;
			}
			
			//삭제할 노드가 루트 노드인 경우 대체할 노드로 바꿈
			if(removeNode == root) {
				root = removeNode;
			} else if (removeNode == ParentNode.leftNode) {
				ParentNode.leftNode = replaceNode;
			} else {
				ParentNode.rightNode = replaceNode;
			}
			
			//삭제 대상노드의 오른쪽 자식 이어감
			replaceNode.rightNode = removeNode.rightNode;
		}
		return true;
	}
	
	/**
     * 중위 순회
     */
    public void inorderTree(Node root, int depth) {
        if (root != null) {
            inorderTree(root.leftNode, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.Vertex);
            inorderTree(root.rightNode, depth + 1);
        }
    }

    /**
     * 후위 순회
     */
    public void postorderTree(Node root, int depth) {
        if (root != null) {
            postorderTree(root.leftNode, depth + 1);
            postorderTree(root.rightNode, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.Vertex);
        }
    }

    /**
     * 전위 순회
     */
    public void preorderTree(Node root, int depth) {
        if (root != null) {
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.Vertex);
            preorderTree(root.leftNode, depth + 1);
            preorderTree(root.rightNode, depth + 1);
        }
    }
    
    
}
class Node {
	int Vertex;
	Node leftNode;
	Node rightNode;
	public Node(int vertex, Node leftNode, Node rightNode) {
		super();
		Vertex = vertex;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	public Node(int value) {
		this.Vertex = value;
	}
}
