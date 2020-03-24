## Git

### 概念
- .git
    - HEAD 指向当前工作的分支引用
    - config 配置信息
    - refs 具体分支指向的commit信息

### 操作
- 查看版本 git --version
- 配置user信息 git config --global user.name '' git config --global user.email ''
- 查看配置 git config -l --global
- 创建项目 git init your_project
    - cd /F/BigData/SpringBootLearning/projects/git_learning
    - 删除一个配置 git config --local --unset user.naem 
    - 添加文件到git git add readme.txt
    - 提交文件 git commit -m"Add readme"
- 查看本地branch信息 git branch -v
- 创建新的分支 git checkout -b temp 5da6a28a47c
- 切换分支 git checkout master
- 工作区直接提交到本地库 git commit -am"Add test"
- 查看日志 git log
    - 简洁版 git log -n4 --oneline
    - 图形版 git log --all --graph
    - 指定branch git log master
    - git图形化界面 gitk
- 查看git当前管理文件状态 git status
- 工作区中已经被git管理，且发生改变的文件一起添加到暂存区 git add -u
- 工作区已经被git管理的文件改名（暂存区或本地库） git mv readme.txt readme.md (快捷方式)
    1. mv readme.txt readme.md
    2. git add readme.md 
    3. git rm readme.txt
- 清理暂存区和工作区 git reset --hard
- 区域
    - 工作目录（区）
    - 暂存区 git add index.html images
    - 版本历史 git commit
- 查看git文件 git cat-file -t 59a4b3  git cat-file -p 59a4b3   
###