# JRGames
JMC游戏服务器主插件

### 注意事项
- 游戏名对应的地图名在config中配置, 一个游戏只能对应一个图
- 游戏最好在玩家游玩完毕后重启

### 编写规范
- `events` 文件下均为自定义的 `event`
- `listeners` 文件下均为事件监听器, 文件名末尾为`EventsListener`
- 所有存储均从 `Storage` 类实现
- **使用驼峰式命名法**