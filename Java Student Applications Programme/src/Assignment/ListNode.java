package Assignment;

public class ListNode 
{
	private Application appDetails;
	private ListNode next;
	
	public ListNode (Application appDetails)
	{
		this.appDetails = appDetails;
		this.next = null;
	}
	public Application getApp() {
		return appDetails;
	}
	public void setApp(Application appDetails) {
		this.appDetails = appDetails;
	}
	public ListNode getNext() {
		return next; 
	}
	public void setNext(ListNode next) {
		this.next = next;
	}
}
