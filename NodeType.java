/** 
 * File: NodeType.java
 * Name: Jake Caruana
 * Email: jtc94964@uga.edu
 * Class: CSCI2720
 * Date Created: 9/27/2024
 */

package project2;

public class NodeType<T extends Comparable<T>>{
	// Data members
	public T info;
	public NodeType<T> next;
	public NodeType<T> back;
	
	// Constructor for new nodes
	public NodeType(T data) {
		this.info = data;
		this.next = null;
		this.back = null;
	}
}
