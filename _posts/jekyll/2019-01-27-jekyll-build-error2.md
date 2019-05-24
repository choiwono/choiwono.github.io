---
layout: post
title:  "jekyll:Layout 'post' requested, cannot load such file -- bundler"
date : 2019-01-27 12:46:32 +0900
categories: jekyll
tags: [jekyll, jekyll-build-error]
---

## 주요 발생 에러메세지
- Build Warning: Layout 'post' requested in jekyll does not exist.
- kernel_require.rb:59:in `require': cannot load such file -- bundler (LoadError)
     
## 환경설정
- ruby 2.5.3p105 (2018-10-18 revision 65156) [x64-mingw32]
- Bundler version 2.0.1
- jekyll 3.8.5     

## jekyll build error 발단 및 해결과정
   
## 발단1
- 간만에,, 포스팅이 늘어남에 따라 주제별로 분류해야 겠다는 생각이 들어 폴더를 구분해 포스팅을 이동시켰다. 
- 그 다음 다시 build를 시키려는데 아래 빌드 경고가 떴다.   
`Build Warning: Layout 'post' requested in jekyll does not exist.`  
- 그리고 _post에 _site폴더가 생기면서 빌드된 파일이 생생되었다 ;
- 찾아보니 내가 빌드 설정을 한 경로가 잘못되었었다;;   
- https://github.com/benbalter/wordpress-to-jekyll-exporter/issues/37

## 발단 2
- 상위경로로가 다시 jekyll build를 실행해보니 이번엔 아래 에러가 떴다..  


{% highlight js %}
C:\workspace\wkimdev.github.io\wkimdev.github.io>jekyll build
Traceback (most recent call last):
        5: from C:/Ruby25-x64/bin/jekyll:23:in `<main>'
        4: from C:/Ruby25-x64/bin/jekyll:23:in `load'
        3: from C:/Ruby25-x64/lib/ruby/gems/2.5.0/gems/jekyll-3.8.5/exe/jekyll:11:in `<top (required)>'
        2: from C:/Ruby25-x64/lib/ruby/gems/2.5.0/gems/jekyll-3.8.5/lib/jekyll/plugin_manager.rb:48:in `require_from_bundler'
        1: from C:/Ruby25-x64/lib/ruby/2.5.0/rubygems/core_ext/kernel_require.rb:59:in `require'
C:/Ruby25-x64/lib/ruby/2.5.0/rubygems/core_ext/kernel_require.rb:59:in `require': cannot load such file -- bundler (LoadError)
{% endhighlight %}   


## 해결과정    
- 구글링 해보니 번들파일을 설치해야 한다고 하여,, 인스톨을 진행했다..
https://github.com/jekyll/jekyll/issues/5165

{% highlight js %}
Try running:

$ gem install bundler
$ bundle install
$ bundle exec jekyll serve
{% endhighlight %}  



- 그런데 bundle exec jekyll serve 후 다시 아래가 떠서 하라는대로 bundle insall를 다시 실행했다.     
{% highlight js %}  
C:\workspace\wkimdev.github.io\wkimdev.github.io> bundle exec jekyll build
Could not find gem 'wdm (~> 0.1.0) x64-mingw32' in any of the gem sources listed in your Gemfile.
Run `bundle install` to install missing gems.

C:\workspace\wkimdev.github.io\wkimdev.github.io>bundle install
Fetching gem metadata from https://rubygems.org/..............
Fetching gem metadata from https://rubygems.org/..
Resolving dependencies...
Fetching public_suffix 3.0.2
Installing public_suffix 3.0.2
Fetching addressable 2.5.2
Installing addressable 2.5.2
Using bundler 1.17.3
Using colorator 1.1.0
....
Fetching wdm 0.1.1
Installing wdm 0.1.1 with native extensions
Bundle complete! 7 Gemfile dependencies, 36 gems now installed.
Use `bundle info [gemname]` to see where a bundled gem is installed.  
{% endhighlight %}      


- 설치가 되었다고 확인한뒤 다시 실행해보았다. 
- 잘된다~~ ^__ ㅠ 
- 단 아래와 같이 실행할 경우 다시 LoadError 에러가 떴다. 

{% highlight js %}  
C:\workspace\wkimdev.github.io\wkimdev.github.io>jekyll serve
Traceback (most recent call last):
        5: from C:/Ruby25-x64/bin/jekyll:23:in `<main>'
        4: from C:/Ruby25-x64/bin/jekyll:23:in `load'
        3: from C:/Ruby25-x64/lib/ruby/gems/2.5.0/gems/jekyll-3.8.5/exe/jekyll:11:in `<top (required)>'
        2: from C:/Ruby25-x64/lib/ruby/gems/2.5.0/gems/jekyll-3.8.5/lib/jekyll/plugin_manager.rb:48:in `require_from_bundler'
        1: from C:/Ruby25-x64/lib/ruby/2.5.0/rubygems/core_ext/kernel_require.rb:59:in `require'
C:/Ruby25-x64/lib/ruby/2.5.0/rubygems/core_ext/kernel_require.rb:59:in `require': cannot load such file -- bundler (LoadError)  
{% endhighlight %}        

   
- 경로에 bundle install 다시 `C:\{USER-DIRECTORY}\wkimdev.github.io>bundle install`
- bundle을 명시한뒤 다시 실행  
`bundle exec jekyll build`    
    
{% highlight js %}        
C:\USERD\wkimdev.github.io>bundle exec jekyll build
Configuration file: C:/workspace/wkimdev.github.io/wkimdev.github.io/_config.yml
            Source: C:/workspace/wkimdev.github.io/wkimdev.github.io
       Destination: C:/workspace/wkimdev.github.io/wkimdev.github.io/_site
 Incremental build: disabled. Enable with --incremental
      Generating...
                    done in 9.265 seconds.
 Auto-regeneration: disabled. Use --watch to enable.
{% endhighlight %}        


- 그냥 jekyll serve라고만 하면 아래 에러가 뜨면서 되지 않는다. 메세지를 읽어보면, 문제를 해결하기 위해 bundle exec를 같이 써서 실행하라고 한다. 

{% highlight js %}        
C:\workspace\wkimdev.github.io\wkimdev.github.io>jekyll serve
Traceback (most recent call last):
        10: from C:/Ruby25-x64/bin/jekyll:23:in `<main>'
         9: from C:/Ruby25-x64/bin/jekyll:23:in `load'
         8: from C:/Ruby25-x64/lib/ruby/gems/2.5.0/gems/jekyll-3.8.5/exe/jekyll:11:in `<top (required)>'
         7: from C:/Ruby25-x64/lib/ruby/gems/2.5.0/gems/jekyll-3.8.5/lib/jekyll/plugin_manager.rb:50:in `require_from_bundler'
         6: from C:/Ruby25-x64/lib/ruby/site_ruby/2.5.0/bundler.rb:107:in `setup'
         5: from C:/Ruby25-x64/lib/ruby/site_ruby/2.5.0/bundler/runtime.rb:26:in `setup'
         4: from C:/Ruby25-x64/lib/ruby/site_ruby/2.5.0/bundler/runtime.rb:26:in `map'
         3: from C:/Ruby25-x64/lib/ruby/2.5.0/forwardable.rb:229:in `each'
         2: from C:/Ruby25-x64/lib/ruby/2.5.0/forwardable.rb:229:in `each'
         1: from C:/Ruby25-x64/lib/ruby/site_ruby/2.5.0/bundler/runtime.rb:31:in `block in setup'
C:/Ruby25-x64/lib/ruby/site_ruby/2.5.0/bundler/runtime.rb:319:in `check_for_activated_spec!': You have already activated public_suffix 3.0.3, but your Gemfile requires public_suffix 3.0.2. Prepending `bundle exec` to your command may solve this. (Gem::LoadError)
{% endhighlight %}    
  


- 다시 실행하니 잘된다~
- bundle exec jekyll serve
  
{% highlight js %}        
C:\workspace\wkimdev.github.io\wkimdev.github.io>bundle exec jekyll serve
Configuration file: C:/workspace/wkimdev.github.io/wkimdev.github.io/_config.yml
            Source: C:/workspace/wkimdev.github.io/wkimdev.github.io
       Destination: C:/workspace/wkimdev.github.io/wkimdev.github.io/_site
 Incremental build: disabled. Enable with --incremental
      Generating...
                    done in 7.671 seconds.
 Auto-regeneration: enabled for 'C:/workspace/wkimdev.github.io/wkimdev.github.io'
    Server address: http://127.0.0.1:4000/
  Server running... press ctrl-c to stop.
      Regenerating: 1 file(s) changed at 2019-01-27 15:30:50
                    _posts/jekyll/test.md
                    ...done in 7.813609 seconds.
{% endhighlight %}   


## 결론
- _post경로가 아닌 '상위 경로'에서 jekyll build를 실행해야 한다. 
- jekyll build가 제대로 실행되면 _site폴더에 build된 포스팅들이 생긴다. 

#### 자주 포스팅을 안하면 잊어버리니까 정리해둔다.      
{% highlight js %}   
  
 1. 포스팅을 작성한다
 2. _posts의 상위경로로가 아래의 명령어로 build를 실행한다.   
 `bundle exec jekyll build`
 3. 같은 디렉토리에서 아래 명령어로 serve를 띄운다.   
 `bundle exec jekyll serve`
 4. 포스팅이 제대로 반영된게 로컬에서 확인되면 commit!     
{% endhighlight %}   


