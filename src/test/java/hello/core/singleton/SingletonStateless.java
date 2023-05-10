package hello.core.singleton;

/**
 * 
 * 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
 */
public class SingletonStateless {

	private int price;	// 싱글톤 객체의 경우 해당 필드는 공유되기 때문에 문제가 발생한다.
	
	/**
	 * 전역변수 사용 - 멀티스레드 환경에서 공유 문제 발생.
	 */
	public void order1(String name, int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	
	
	/**
	 * 1. 지역변수 사용
	 */
	public int orderLocalVar(String name, int price) {
		int orderPrice = price;
		return orderPrice;
	}
	
	/**
	 * 2. 파라미터 사용
	 */
	
	public int orderParam(String name, int price) {
		return price;
	}
	
	/**
	 * 3. ThreadLocal 사용
	 */
	private ThreadLocal<Integer> orderPriceThreadLocal = new ThreadLocal<>();
	public void orderThreadLocal(String name, int price) {
		orderPriceThreadLocal.set(price);
	}
	public int getPriceThreadLocal() {
		return orderPriceThreadLocal.get();
	}
}
