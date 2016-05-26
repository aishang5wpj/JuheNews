JuheNews系列之一 · 前言
-
做安卓两年多，技术日新月异，虽然每天都在敲代码，但是感觉其实一直都是用的5.0及以前的那些控件、特效、风格。

今天是2016年5月26日，Android N发布已经一周了，什么Material Design，什么mvp、mvvm、data binding，之前统统都没用过，回头想想，觉得作为一个安卓程序员真是失败啊。

前段时间研究MVP模式时，发现了一个不错的项目SimpleNews。
> 文章地址：[http://www.cnblogs.com/liuling/archive/2015/12/23/mvp-pattern-android.html](http://www.cnblogs.com/liuling/archive/2015/12/23/mvp-pattern-android.html)
> 
> GitHub链接:[https://github.com/liuling07/SimpleNews](https://github.com/liuling07/SimpleNews)

抱着学习的心态把它clone下来研究了几天，然后自己也照着里面的代码实现了一个。

听上去大家可能会觉得我好像只是复制粘贴，然后换了几个api而已。我想说其实不是，虽然我也会看原来项目的那些实现，但是我更多的是看他用了哪些控件，然后我去百度那个控件的用法自己来实现。当然对于原来项目中我觉得写的好的实现我也会参考一下，毕竟是来向人家学习，我觉得没什么好丢脸的。

实现的过程中我也加入了自己的想法，让整个项目从里到外都变得更实用、好看。

似乎所有程序员都有开源的情节，原来看那些大牛的开源项目时，感觉很羡慕很佩服，他们不仅技术牛逼，而且愿意分享自己的知识，所以有时候我也会想什么时候能有自己的开源项目，能帮助别人一点也好。

这个项目虽然并没有多么牛逼的技术，更多的是对MVP和Material Design的理解和运用，不过也算是自己的一次尝试，每天埋头写代码但是没有分享和总结那不是成了代码机器了吗。

要说的就是这些了，后面我会对这个项目每个点来记录和总结下，以免以后要用的时候又去百度。

项目已经开源：
> github链接：[https://github.com/aishang5wpj/JuheNews](https://github.com/aishang5wpj/JuheNews)

下面是效果图。

AppBarLayout+ToolBar+DrawerLayout+NavigationView
--
![image](https://github.com/aishang5wpj/JuheNews/raw/master/images/DrawerLayout+NavigationView.gif)

TabLayout+ViewPager
--
![image](https://github.com/aishang5wpj/JuheNews/raw/master/images/TabLayout+ViewPager.gif)
 
SwipeRefershLayout下拉刷新+RecylerView上拉加载
--
![image](https://github.com/aishang5wpj/JuheNews/raw/master/images/SwipeRefershLayout+RecylerView.gif)
 
RecyclerView+CardView
--
![image](https://github.com/aishang5wpj/JuheNews/raw/master/images/RecyclerView+CardView.gif)
 
CoordinatorLayout+CollapsingToolbarLayout+NestedScrollView
--
![image](https://github.com/aishang5wpj/JuheNews/raw/master/images/CoordinatorLayout+CollapsingToolbarLayout+NestedScrollView.gif)