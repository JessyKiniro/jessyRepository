# PirctureSelector

## RecyclerView

> 新控件 RecyclerView，提供了一种插拔式的体验，高度解耦，设置它提供不同 LayoutManager、ItemDecoration，ItemAnimator 实现 ListView,GridView,瀑布流等效果

### LayoutManager：布局管理器

RecyclerView 的布局管理器是一个抽象类，系统实现了三个实现类

> LinearLayoutManager 线性布局管理器，支持横向或纵向选择
> GridLayoutManager 网格布局管理器
> StaggeredGridManager 瀑布流 布局管理器

### RecyclerView 基本使用

有以下几个步骤

1. 类引用
2. 初始化控件
3. 设置布局管理器
4. 设置 adapter
5. 添加分割线
6. 设置 Item 的增删动画
7. 添加 Item 的点击，长按时间

### ItemDecoration-分割线

> 通过 mRecyclerView.addItemDecoration(RecyclerView.ItemDecoration itemDecoration)方法添加分割线，ItemDecoration 类为抽象类，需要自己实现
> 当调用 mRecyclerView.addItemDecoration()方法添加 decoration 的时候，Recycler 绘制
