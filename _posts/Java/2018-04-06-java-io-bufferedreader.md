---
layout: post
title:  "Java 입출력, BufferedReader, StringTokenizer"
date : 2018-04-06 12:46:32 +0900
description: 입출력문제는 기본적이라 생각하고 대충넘어가려 했었나보다;;.. 알고리즘에서는 처리속도를 줄이는게 중요한데 이번기회에 확실히 알고 넘어가자.
categories: Java
tags: Java BufferedReader StringTokenizer
---

# 자바의 입력 Class 
- **Scanner**, **BufferedReader**, **StringTokenizer**
- BufferedReader, StringTokenizer 는 문자열로 활용하기 위하여 사용. BufferedReader를 사용하는 것이 Scanner를 사용하는 것보다 빠르다.
- BufferedReader는 문자열에 최적화 되어 있음.

- Scanner를 사용했을시 입력 형태.

{% highlight js %}
//Scanner를 사용했을시 입력 형태.
Scanner sc = new Scanner(System.in);

int n = sc.nextInt(); // int
long l = sc.nextLong(); // int
String s = sc.next(); // String
String s = sc.nextLine(); // String

{% endhighlight %}

## BufferedReader를 사용할때
- 아래와 같이 한줄로 입력시, sc.nextInt()를 12번 호출하는건 비효율적이다. 입력갯수가 큰 알고리즘 문제의 경우 시간제한에 걸리게 된다.

{% highlight js %}
1 2 3 4 5 6 7 8 9 10 11 12 // 한줄 입력

for(int i=0;i<12;i++) {
sc.nextInt();
}
{% endhighlight %}

- 그래서 아래와 같이 사용한다.
- 문자열로 받고 split메소드를 이용해서 공백을 기준으로 잘라서 활용.
- integer.parseInt() 형변환을 통해 사용.

{% highlight js %}
1 2 3 4 5 6 7 8 9 10 11 12 // 한줄 입력

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String[] s = br.readLine().split(" ");

// s[0] = "1"; Integer.parseInt(s[0]) => 1
// s[1] = "2";
// s[2] = "3";
// .....

{% endhighlight %}

## StringTokenizer 사용목적
- BufferedReader는 잘라서 배열과 같이 인덱스를 사용하여 접근하여 사용.
- StringTokenizer는 공백이 있다면 뒤에 문자열이 공백 자리를 땡겨 채우도록 한다.
- StringTokenizer가 BufferedReader보다 빠르게 사용될 수 있다.
- 문자열을 자르게 위해 split을 사용할땐, split은 정규식을 기반으로 자르는 로직으로서 내부는 복잡하다. 그에 비해 StringTokenizer의 nextToken()메소드는 단순히 공백 자리를 땡겨 채우는 것이다. 그렇기 때문에 속도 차이가 날 수 밖에 없다.
- 정규식이나 인덱스 접근과 같은 처리가 필요없다면 StringTokenizer를 사용하는 것이 효율적이다.

{% highlight js %}
BufferedReader br = new BufferedReader(new InputStreamReader(System.in);
StringTokenizer st = new StringTokenizer(br.readLine());

// AB CDD EFFF GH 입력

st.nextToken() // AB
st.nextToken() // CDD
st.nextToken() // EFFF
st.nextToken() // GH
{% endhighlight %}

## StringTokenizer 사용법
- 자바에서는 String을 token단위로 끊어주는 StringTokenizer 클래스를 제공한다.
- 예를들어 "this is my string" 이라는 스트링을 this, is, my, string 4개의 스트링으로 끊어주는 기능을 제공한다.
- 그리고 공백말고도 다른 구획문자(delimiter)를 사용할수도 있다. 예를들어 this%is%my%string을 delimiter에 %를 넣어 StringTokenizer를 사용하면 마찬가지로 this, is, my, string으로 읽어준다.
- this$%^is$my%string^일때 구획문자를 "$%^"라고 설정해주면 this, is, my, string 으로 끊어준다.

{% highlight js %}
    String str = "this%%is%%my%%string"; 
    StringTokenizer st = new StringTokenizer(str,"%%"); 

    while(st.hasMoreTokens()) { 
        System.out.println(st.nextToken()); 
    }
{% endhighlight %}



## 같은 문제를 풀었을 때, Scanner와 BufferedReader를 사용했을 때의 처리속도차이
- BufferedReader를 사용했을시, 92MS로 처리속도 단축.
 ![이미지](/post_assets/2018-04-08/algoInput.jpg)

- 알고리즘 문제를 풀때, 입력이 몇개인지 주어지지 않는 경우 입력을 EOF(End Of File)까지 처리한다. eof를 사용함으로서 데이터가 없음을 알려줄 수 있다.
- java에서는 `while(sc.hasNextInt())`를 사용함으로서 EOF 까지 입력을 받을 수 있음.(입력이 끝날때까지)
- ^ : 뺀다는것
- java `readline, nextLine(한줄바로 입력받음)`


#### 출처
- [http://mygumi.tistory.com/78] 
- [J. deo의 그알정보](http://arer.tistory.com/48)
- [Scanner 와 BufferedReader 의 속도 및 메모리 차이](https://m.blog.naver.com/PostView.nhn?blogId=mycho&logNo=220845741136&categoryNo=0&proxyReferer=&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F)
- [[JAVA] 문자 Stream : BufferedReader / BufferedWriter (파일 복사 예제)](http://hyeonstorage.tistory.com/249)
- [[JAVA 자바] StringTokenizer](http://arer.tistory.com/48)
- [자바로 eof 처리 어떻게 해야하는건가요?ㅠㅠ](https://www.acmicpc.net/board/view/6682)
- [[JAVA] Scanner 클래스의 next()와 nextLine() 메서드의 차이점](http://sexy.pe.kr/tc/496)
- [Java EOF 처리](http://mygumi.tistory.com/236)
- [자바 입력 클래스 활용하기 :: 마이구미](http://mygumi.tistory.com/78)
