package com.buguagaoshu.community.controller.api;

import com.buguagaoshu.community.dto.CommentCreateDto;
import com.buguagaoshu.community.dto.CommentDto;
import com.buguagaoshu.community.dto.ResultDTO;
import com.buguagaoshu.community.exception.CustomizeErrorCode;
import com.buguagaoshu.community.model.Comment;
import com.buguagaoshu.community.model.User;
import com.buguagaoshu.community.service.CommentService;
import com.buguagaoshu.community.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.buguagaoshu.community.dto.ResultDTO.okOf;

/**
 * @author Pu Zhiwei {@literal puzhiweipuzhiwei@foxmail.com}
 * create          2019-08-31 14:22
 */
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/api/comment")
    public ResultDTO insertComment(@RequestBody CommentCreateDto commentDto, String captcha,
                                   HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentDto == null || commentDto.getContent() == null || commentDto.getContent().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setQuestionId(commentDto.getQuestionId());
        comment.setParentId(commentDto.getParentId());
        comment.setType(commentDto.getType());
        comment.setCommentator(user.getId());
        comment.setContent(commentDto.getContent());
        comment.setCreateTime(StringUtil.getNowTime());
        comment.setModifiedTime(StringUtil.getNowTime());
        commentService.insertComment(comment);
        return okOf(comment);
    }

    @GetMapping("/api/twoLevelComment/{commentId}")
    public ResultDTO backTwoLevelComment(@PathVariable("commentId") String commentId) {
        List<CommentDto> commentDtos = commentService.getTwoLevelCommentByParent(commentId, 2);
        return ResultDTO.okOf(commentDtos);
    }
}
