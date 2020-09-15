# ReviveCoinReremake

---

![LICENSE](https://img.shields.io/github/license/Micalhl/ReviveCoinReremake)
![Issues](https://img.shields.io/github/issues/Micalhl/ReviveCoinReremake)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/Micalhl/ReviveCoinReremake?style=flat-square)
![Last Commit](https://img.shields.io/github/last-commit/Micalhl/ReviveCoinReremake)
![Pull Request](https://img.shields.io/github/issues-pr/Micalhl/ReviveCoinReremake)

---

## 版本信息

- 支持服务端版本: `spigot/... 1.13-1.16`
- 当前插件版本: `2.5-Alpha`
- 
- ParrotX版本: `1.3.8-Alpha`
- I18n信息:
- -> 版本: `[EP's Locale Tool] v3.1`
- -> 绑定插件: `ReviveCoinReremake`
- -> 语言: `Chinese`

## 插件信息
- 作者: Mical
- ParrotX作者: EntityParrot
- 插件依赖: [Vault]
- 插件软依赖: [PlaceholderAPI]

## 开发者API
- ReviveCoinEvent 事件, 玩家复活币事件, 有两个构造器, 一个是为`add`和`take`做准备一个是为`give`做准备, `type`为类型
- CoinUtils:<br>

| 方法名  | 描述 |
|----: | :---- |
|add(Player player, int coins)|为指定玩家增加指定数量复活币|
|take(Player player, int coins)|为指定玩家减少指定数量复活币|
|give(Player user1, Player user2, int coins)|转移玩家1账户内指定数量复活币到玩家2|
|has(Player player, int coins)|判断玩家是否有指定数量复活币|
|get(Player player)|获得指定玩家的复活币数量|

## Placeholders

| 名称  | 描述 |
|----: | :---- |
|revivecoin_coin|复活币数量|

## 鸣谢
- Karlatemp -> 优化/修复代码