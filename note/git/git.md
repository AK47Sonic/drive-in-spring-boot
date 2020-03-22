## Git
- 查看版本 git --version
- 配置user信息 git config --global user.name '' git config --global user.email ''
- 查看配置 git config -l --global
- 创建项目 git init your_project
    - cd /F/BigData/SpringBootLearning/projects/git_learning
    - 删除一个配置 git config --local --unset user.naem 
    - 添加文件到git git add readme.txt
    - 提交文件 git commit -m"Add readme"