package com.github.anicmv.Inline.impl;//package com.github.anicmv.Inline.impl;

import com.github.anicmv.Inline.InlineQueryResultProvider;
import com.github.anicmv.contant.BotConstant;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anicmv
 * @date 2025/3/30 00:48
 * @description 1.随机图片
 */
@Component
class RandomScyImageQueryResultProvider implements InlineQueryResultProvider {

    @Override
    public String getSortId() {
        return BotConstant.N_2;
    }

    @Override
    public InlineQueryResult createResult(InlineQuery inlineQuery) {
        String imageUrl = "https://jpg.moe/i/yj9rnewl.jpeg";
        String fullName = inlineQuery.getFrom().getFirstName() +
                (inlineQuery.getFrom().getLastName() == null ? "" : inlineQuery.getFrom().getLastName());
        // 同时附加内联键盘按钮实现交互
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text("\uD83D\uDE12" + fullName + "️\uD83D\uDE12")
                .callbackData(BotConstant.CALLBACK_RANDOM_SCY)
                .build();

        InlineKeyboardRow row = new InlineKeyboardRow();
        row.add(button);
        List<InlineKeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
        return InlineQueryResultPhoto.builder()
                .id(getSortId())
                .photoUrl(imageUrl)
                .thumbnailUrl(imageUrl)
                .title("随机三次元")
                .replyMarkup(markup)
                .build();
    }


}
