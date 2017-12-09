package com.johnny.cms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.johnny.cms.bean.Msg;
import com.johnny.cms.domain.Product;
import com.johnny.cms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Johnny
 * Date: 2017/11/29
 * Time: 下午3:08
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 删除数据
     *
     * @param ids ids
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids) {
        //批量删除
        if (ids.contains("-")) {
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            productService.deleteBatch(del_ids);
        } else {
            //单独删除
            Integer id = Integer.parseInt(ids);
            productService.deleteEmp(id);
        }
        return Msg.success();
    }

    /**
     * TODO: null while use PUT method here and index.jsp
     * but POST is OK
     * 商品更新方法
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{proId}", method = RequestMethod.POST)
    public Msg saveEmp(Product product) {
        System.out.println("将要更新的商品数据：" + product);
        productService.updateEmp(product);
        return Msg.success();
    }

    @ResponseBody
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @RequestMapping("/search")
    @SuppressWarnings("unchecked")
    public Msg search(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                      @RequestParam("proName") String str) {
        System.out.println("待搜索的商品名关键字为：" + str);

        PageHelper.startPage(pn, 5);
        List<Product> pros;
        //查询关键字为空，则查询所有
        if (str.equals("")) {
            pros = productService.getAll();
        } else {
            pros = productService.search(str);
        }
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 根据id查询商品
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Product product = productService.getEmp(id);
        return Msg.success().add("emp", product);
    }

    /**
     * 检查商品名是否已存在
     */
    @ResponseBody
    @RequestMapping("/check")
    public Msg checkExist(@RequestParam("proName") String proName) {
        //先判断商品名是否合法;
        //这里也可以放在前端页面，在发送ajax请求之前。保证和js的验证一致
        String regex = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]+)";
        if (!proName.matches(regex)) {
            return Msg.fail().add("va_Message", "商品名必须是中文开头或者3-16位英文和数字的组合");
        }

        //数据库商品名重复校验
        boolean b = productService.checkExist(proName);
        if (!b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_Message", "商品名不可用");
        }
    }

    /**
     * 保存数据
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            //保存
            productService.saveEmp(product);
            return Msg.success();
        }
    }

    /**
     * 分页显示数据功能
     * TODO：不能只展示第一页数据
     *
     * @param pn pn
     * @return message
     */
    @RequestMapping("/pros")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Msg getCommodityWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 利用PageHelper插件进行分页查询
        // 输入页码以及每页的数据量
        PageHelper.startPage(pn, 5);
        // 这里顺序是不能乱的，必须先调用方法startPage然后在查询
        List<Product> pros = productService.getAll();
        // 用pageInfo封装查询结果，然后将其传给页面
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(pros, 5);
        return Msg.success().add("pageInfo", page);
    }

}
