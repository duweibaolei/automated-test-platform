------

# Git 命令速查手册

------

## 一、查看文件状态

### 1.1 基本查看：`git status`

运行 `git status` 可查看当前工作区的文件状态：

| 标识       | 含义           | 说明                          |
| ---------- | -------------- | ----------------------------- |
| 🔴 红色文件 | 已修改但未暂存 | 需先执行 `git add`            |
| 🟢 绿色文件 | 已暂存但未提交 | 可直接 `git commit`           |
| `??` 标记  | 未跟踪的新文件 | 未被 Git 管理，需先 `git add` |

**示例输出：**

```
Changes not staged for commit:    # 未暂存的修改（红色）
    modified:   README.md

Untracked files:                  # 未跟踪的新文件（红色 ??）
    ?? new_file.txt
```

### 1.2 精简模式：`git status -s`

更紧凑的输出格式，适合快速扫描：

```bash
git status -s
```

**状态标识含义：**

- **左列字母**：暂存区状态（`A`=新增，`M`=修改，`D`=删除）
- **右列字母**：工作区状态（同左列规则）
- `??`：未跟踪文件

```
 M README.md      # 工作区修改但未暂存
A  new_file.txt   # 已暂存的新文件
?? temp.log       # 未跟踪文件
```

### 1.3 查看具体修改内容

| 命令                   | 用途                                 |
| ---------------------- | ------------------------------------ |
| `git diff`             | 查看未暂存的修改（工作区 vs 暂存区） |
| `git diff --staged`    | 查看已暂存的修改（暂存区 vs 仓库）   |
| `git diff --name-only` | 仅列出变更文件名                     |

------

## 二、暂存与提交

### 2.1 暂存文件

提交前必须先将修改添加到暂存区：

```bash
# 暂存单个文件
git add <文件名>

# 暂存所有修改（新增、修改、删除）
git add .

# 暂存所有文件（包括未跟踪的新文件）
git add -A
```

### 2.2 核心提交命令

#### 基本提交

```bash
git commit -m "提交信息"
```

> ⚠️ 提交信息应说明**修改目的**而非操作过程（避免写"修改了 a.js"）。

#### 跳过暂存区直接提交

```bash
git commit -a -m "提交信息"
```

`-a` 会自动暂存所有**已跟踪文件**的修改（不包含新文件）。

#### 修改上次提交

```bash
git commit --amend
```

可修改提交信息或补充遗漏文件。

> ⚠️ **注意：** 此操作会重写最后一次提交的哈希值。若已推送到远程仓库，需 `git push --force`，团队协作中应避免修改已共享的提交。

------

## 三、提交信息规范

### 3.1 结构化格式（推荐）

```
<类型>(<作用域>): <简短描述>

<详细说明>

<关联 issue>
```

### 3.2 类型说明

| 类型       | 含义          |
| ---------- | ------------- |
| `feat`     | 新增功能      |
| `fix`      | 修复 Bug      |
| `docs`     | 文档修改      |
| `style`    | 代码格式调整  |
| `refactor` | 代码重构      |
| `test`     | 测试相关      |
| `chore`    | 构建/工具变更 |

### 3.3 示例

```
feat(auth): 添加短信验证码登录功能

修复了 #123 中验证码过期时间错误的问题
```

### 3.4 关键原则

- ✅ **必须填写**提交信息，Git 禁止空信息提交
- ✅ 说明**为什么修改**，而非如何修改
- ✅ 多次小提交优于单次大提交，每个提交聚焦单一功能
- ❌ 避免模糊信息如 `"update files"`

------

## 四、双远程仓库管理

> 适用场景：`origin` 指向自己的 Fork 仓库，`upstream` 指向原始开源仓库。

### 4.1 基础配置与验证

**查看远程仓库：**

```bash
git remote -v
```

典型输出：

```
origin    https://github.com/your-username/repo.git (fetch)
origin    https://github.com/your-username/repo.git (push)
upstream  https://github.com/original-owner/repo.git (fetch)
upstream  https://github.com/original-owner/repo.git (push)
```

**设置本地分支默认追踪 `origin`：**

```bash
git branch --set-upstream-to=origin/main main
```

### 4.2 日常开发（针对 `origin`）

```bash
# 1. 暂存修改
git add .

# 2. 提交
git commit -m "描述性提交信息"

# 3. 推送到 Fork 仓库
git push

# 4. 拉取远程更新
git pull
```

### 4.3 同步上游仓库（针对 `upstream`）

#### 步骤一：获取上游代码

```bash
git fetch upstream
```

> 此操作仅下载代码到本地缓存，不修改本地文件。

#### 步骤二：同步到本地分支

**方案一：合并（保留合并历史）**

```bash
git checkout main
git merge upstream/main
```

**方案二：变基（推荐，保持线性历史）**

```bash
git checkout main
git rebase upstream/main
```

冲突解决流程：

```bash
# 1. 修复冲突文件
# 2. 标记解决
git add <冲突文件>
# 3. 继续变基
git rebase --continue
```

#### 步骤三：推送到 `origin`

```bash
git push -f origin main
```

> ⚠️ 仅推送至 `origin`，绝不可推送至 `upstream`。

### 4.4 风险规避

**禁止直接推送至 `upstream`：**

```bash
git remote set-url --push upstream no_push
```

**明确区分拉取来源：**

```bash
git pull origin main      # 同步自己的 Fork 仓库
git pull upstream main    # 同步原始仓库
```

### 4.5 典型场景：向原始仓库提 PR

```bash
# 1. 同步上游最新代码
git fetch upstream
git checkout main
git rebase upstream/main

# 2. 创建功能分支
git checkout -b feat/new-feature

# 3. 开发并提交
git add .
git commit -m "feat: Add new feature"
git push

# 4. 在 GitHub/GitLab 上提交 Pull Request
```

------

## 五、常见错误提醒

| 错误                        | 正确做法                                  |
| --------------------------- | ----------------------------------------- |
| 未暂存直接提交              | 先 `git add`，或使用 `git commit -a`      |
| 模糊提交信息                | 使用规范格式：`fix: 修复用户头像上传失败` |
| 混淆 `origin` 和 `upstream` | 操作前用 `git remote -v` 确认目标         |
| 修改已共享的提交            | 避免对已推送的提交使用 `--amend`          |

