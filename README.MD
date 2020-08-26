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
- 当前插件版本: `2.2-SNAPSHOT`
- 
- ParrotX版本: `1.2.1-Alpha`
- I18n信息:
- -> 版本: `[EP's Locale Tool] v2.1`
- -> 绑定插件: `ReviveCoinReremake`
- -> 语言: `Chinese`

## 插件信息
- 作者: Mical
- ParrotX作者: EntityParrot
- MicalLib作者: Mical
- 插件依赖: [Vault, ParrotX, MicalLib]
- 插件软依赖: [PlaceholderAPI]

## TODO
- 本地存储
- 优化代码
- 更新PAPI √

## 开发者API
- CoinAPI 您可以自己新建一个类来实现CoinAPI, 然后自己写方法<br>
- ReviveCoinAddEvent 事件, 玩家复活币增加事件,`player`玩家对象,`coins`增加的复活币数量<br>
- ReviveCoinReduceEvent 事件, 玩家复活币减少事件,`player`玩家对象,`coins`减少的复活币数量<br>
- ReviveCoinGiveOtherEvent 事件, 玩家赠与他人复活币事件,`user1`赠予者,`user2`被赠予者,`coins`赠予复活币数量<br>
- ReviveCoin:<br>

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