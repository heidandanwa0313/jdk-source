/*
*date:20160604
*author:ameng
*collection源码阅读
*/
1、AbstractCollection<E>
此类提供了Collection接口最基础的实现，最大限度地减少了实现此接口所需的工作
2、 ArrayDeque<E>
 public void addFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[head = (head - 1) & (elements.length - 1)] = e;		//防止下标越界或者不合法
        if (head == tail)
            doubleCapacity();		//队列已满，则需要进行扩容
    }
      private boolean delete(int i)方法没看懂
      615行
      
