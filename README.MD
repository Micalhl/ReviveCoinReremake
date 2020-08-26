# ReviveCoinReremake

## 版本信息

- 支持服务端版本: spigot/... 1.13-1.16
- 当前插件版本: 2.2-SNAPSHOT
- 
- ParrotX版本: 1.2.1-Alpha
- I18n信息:
- -> [EP's Locale Tool] v2.1
- -> 绑定插件: ReviveCoinReremake
- -> 语言: Chinese

## 插件信息
- 作者: Mical
- ParrotX作者: EntityParrot
- MicalLib作者: Mical
- 插件依赖: [Vault, ParrotX, MicalLib]
- 插件软依赖: [PlaceholderAPI]

## TODO
- 本地存储
- 优化代码
- 更新PAPI

## 开发者API
- CoinAPI 您可以自己新建一个类来实现CoinAPI, 然后自己写方法<br>
- ReviveCoinAddEvent 事件, 玩家复活币增加事件 player玩家对象 coins增加的复活币数量<br>
- ReviveCoinReduceEvent 事件, 玩家复活币减少事件 player玩家对象 coins减少的复活币数量<br>
- ReviveCoinGiveOtherEvent 事件, 玩家赠与他人复活币事件 user1赠予者 user2被赠予者 coins赠予复活币数量<br>
- ReviveCoin:<br>
- -> add方法: 为指定玩家增加指定数量复活币
- -> take方法: 为指定玩家减少指定数量复活币
- -> give方法: 转移玩家1账户内指定数量复活币到玩家2
- -> has方法: 判断玩家是否有指定数量复活币
- -> get方法： 获得指定玩家的复活币数量

## Placeholder
- %revivecoin_coins% 当前拥有复活币数量