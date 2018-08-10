package com.zyyan.ms.common.util;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zyyan.ms.common.constant.JWTConstants;
import com.zyyan.ms.common.entity.JWTInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtils {
	
	private static RsaKeyUtils rsaKeyUtils = new RsaKeyUtils();
	
	/**
	 * 公钥加密
	 * 
	 * @param jwtInfo
	 * @param pubKeyPath
	 * @param expire
	 * @return
	 * @throws Exception
	 */
	public static String generateTokenWithPublicKey(JWTInfo jwtInfo, String pubKeyPath, int expire) throws Exception {
		PublicKey publicKey = rsaKeyUtils.getPublicKey(pubKeyPath);
		String token = Info2Token(jwtInfo, publicKey, expire);
        return token;
	}
	
    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(JWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
    	PrivateKey privateKey = rsaKeyUtils.getPrivateKey(priKeyPath);
    	String token = Info2Token(jwtInfo, privateKey, expire);
        return token;
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(JWTInfo jwtInfo, byte priKey[], int expire) throws Exception {
    	PrivateKey privateKey = rsaKeyUtils.getPrivateKey(priKey);
    	String token = Info2Token(jwtInfo, privateKey, expire);
        return token;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
    	PublicKey publicKey = rsaKeyUtils.getPublicKey(pubKeyPath);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        return claimsJws;
    }
    
    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyUtils.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }
    
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromToken(String token, String pubKeyPath) {
        Jws<Claims> claimsJws;
		try {
			claimsJws = parserToken(token, pubKeyPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
        Claims body = claimsJws.getBody();
        JWTInfo jwtInfo = body2Info(body);
        return jwtInfo;
    }
    
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        JWTInfo jwtInfo = body2Info(body);
        return jwtInfo;
    }
    
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
    
    private static JWTInfo body2Info(Claims body) {
    	String userId = getObjectValue(body.get(JWTConstants.JWT_KEY_USER_ID));
        String name = getObjectValue(body.get(JWTConstants.JWT_KEY_NAME));
        JWTInfo jwtInfo = new JWTInfo(userId,name);
        return jwtInfo;
    }

    @SuppressWarnings({ "unchecked", "unused" })
	private static List<Object> getListValue(Object object) {
		return object==null?null:(List<Object>)object;
	}

	private static String Info2Token(JWTInfo jwtInfo, Key key, int expire){
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.SECOND, expire);
		Date expiration = ca.getTime();
    	String token = Jwts.builder()
        		.setSubject(jwtInfo.getName())
        		.claim(JWTConstants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(JWTConstants.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.RS256, key)
                .compact();
    	return token;
    }
}
