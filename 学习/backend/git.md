# Git

### git 版本控制
1. `git reset --hard head^^/head~3/1094a`回退到某个版本
2. `git log`查看提交历史
3. `git reflog`记录命令历史
4. `git status`查看状态
5. `git checkout -- filename`回到最近一次git commit或git add的状态
6. `git reset HEAD filename`把暂存区的更改回退到工作区

### git 远程库
1. `git remote add origin 'github.com...'`添加远程库
2. `git push -u origin master`把当前分支推送到远程库  
   `-u` 将本地的master和远程的master联合起来

### git 分支控制

[廖雪峰博客讲的很明白](https://www.liaoxuefeng.com/wiki/896043488029600/900003767775424)

1. `git checkout -b dev`创建dev分支,然后切换到dev分支  
    `-b` 创建分支,相当与branch
2. `git merge dev`合并dev到当前分支
3. `git branch -d dev`删除dev分支  
    `-D` 强制删除
4. 合并分支时,通常使用fast forward模式,看不出来合并历史.开发中使用--no-ff参数禁止快速合并.
5. 如果出现冲突,解决掉冲突以后commit
6. `git log --graph --pretty=oneline --abbrev-commit`可以查看合并历史

### git 多人协作
1. `git stash`把不想提交的内容暂存起来
2. `git stash list/apply/drop/pop`列出,恢复,删除,恢复并删除
3. `git cherry-pick commit_id`复制一份提交到此分支
4. `git checkout -b dev origin/dev`创建dev分支与远程对应