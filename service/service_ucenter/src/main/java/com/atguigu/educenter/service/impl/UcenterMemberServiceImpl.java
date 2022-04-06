package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.educenter.utils.MD5;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Ay
 * @since 2022-03-18
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 登录接口
     *
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录表单不全");
        }
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("email",email));
        if(null == member){
            throw new GuliException(20001,"邮箱错误");
        }
        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001,"密码错误");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    /**
     * 注册接口
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String email = registerVo.getEmail();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if(StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new GuliException(20001,"参数错误");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(email);
        if(!code.equals(mobleCode)) {
            throw new GuliException(20001,"验证码错误");
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("email", email));
        if(count.intValue() > 0) {
            throw new GuliException(20001,"error");
        }

        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setEmail(registerVo.getEmail());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://aynihao.oss-cn-guangzhou.aliyuncs.com/2022/03/11/3bc92953-679e-4923-87b5-43781bb76fdb.png");
        this.save(member);
    }

    /**
     * 获得用户信息并返回
     *
     * @param memberId
     * @return
     */
    @Override
    public LoginInfoVo getLoginInfo(String memberId) {
        UcenterMember member = this.getById(memberId);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(member,loginInfoVo);
        return loginInfoVo;
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("openid", openid));
        return member;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }
}
