package com.example.notebook.dto;

import com.example.notebook.entity.NoticeType.TypeNoticeDefault;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO {

  String title;

  String content;

  Long ownerId;

  TypeNoticeDefault type;

}
