package com.github.anicmv.command.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.anicmv.command.BotCommand;
import com.github.anicmv.contant.BotConstant;
import com.github.anicmv.entity.Diss;
import com.github.anicmv.mapper.DissMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author anicmv
 * @date 2025/3/30 00:48
 * @description /diss
 */
@Component
public class DissCommand implements BotCommand {

    @Resource
    private DissMapper dissMapper;

    @Override
    public boolean supports(String commandText) {
        return commandText.trim().startsWith(BotConstant.DISS);
    }

    @Override
    public SendMessage execute(Update update) {
        long chatId = update.getMessage().getChatId();
        // 使用 MyBatis-Plus 通过 LambdaQueryWrapper 的 last 方法添加 ORDER BY RAND() LIMIT 1
        // 注意：该 SQL 语法依赖于具体数据库，如 MySQL 中使用 RAND() 函数
        Diss diss = dissMapper.selectOne(
                new LambdaQueryWrapper<Diss>().last("ORDER BY RAND() LIMIT 1")
        );

        String text = (diss != null && diss.getContent() != null)
                ? diss.getContent()
                : "没有找到吐槽内容哦！";
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }
}