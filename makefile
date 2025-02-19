usage:
	@echo '==============================================================================================================='
	@echo 'usage                       :     显示本菜单'
	@echo 'clean                       :     清理本项目'
	@echo 'compile                     :     编译项目'
	@echo 'build                       :     构建项目'
	@echo 'dist                        :     发布项目'
	@echo 'test                        :     执行单元测试'
	@echo 'setup-gradle-wrapper        :     设置gradle-wrapper'
	@echo 'dependencies                :     分析若干模块依赖关系'
	@echo 'github                      :     提交文件'
	@echo '==============================================================================================================='

clean:
	@$(CURDIR)/gradlew --quiet -p $(CURDIR) clean
	@#$(CURDIR)/gradlew --quiet -p $(CURDIR)/build-src clean

compile:
	@$(CURDIR)/gradlew classes

build:
	@$(CURDIR)/gradlew -x test build

dist:
	@$(CURDIR)/gradlew -x test dist

test:
	@$(CURDIR)/gradlew test

setup-gradlew-wrapper:
	@$(CURDIR)/gradlew wrapper

dependencies:
	@$(CURDIR)/gradlew :projects-app:daemon:dependencies

github:
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"

.PHONY: usage clean compile build dist test setup-gradle-wrapper dependencies github
