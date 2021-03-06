package com.rpo.spring_back.tools;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;

// Здесь будут разные полезные функции, требующиеся в разных местах приложения.
public class Utils {

    // Функция вычисляет SHA-256 по конкатенации строк пароля и salt. Она потребуется в UserController
    // для смены пароля пользователя и в LoginController для проверки пароля. Эта функция получает на вход
    // пароль в виде обычной строки и salt в виде строки в формате HEX-ASCII. salt - это случайная последовательность
    // байтов, которая генерируется каждый раз при смене пароля. Перед тем как передать salt функции ComputeHash
    // массив байтов преобразуется в строку, каждая пара символов которой представляет в шестнадцатеричной системе
    // в текстовом виде один байт. В такой строке могут быть только цифры от 0 до 9 и буквы от A до F. В
    // этой функции пароль преобразуется в массив байтов, затем этот массив преобразуется в HEX-ASCII и склеивается
    // с salt. Затем все это преобразуется обратно в байты и по этим байтам считается хеш SHA256.
    // Это значение преобразуется в HEX-ASCII и возвращается как результат работы функции.
    // ***
    // Зачем так делать? Хранить пароли в базе в чистом виде не рекомендуется по соображениям безопасности, но
    // там можно хранить хеши паролей. Функция вычисления хеша работает в одну сторону, поэтому из хеша нельзя
    // восстановить пароль, но можно проверить введенный пароль, вычислив его хеш и сравнив с копией в базе.
    // Зачем нужен salt? Можно использовать пароли qwerty или 123 или что то в этом роде. Можно составить словарь,
    // состоящий их хешей таких простых паролей, и легко подобрать нужное значение. Кроме этого, если у двух
    // пользователей в системе будут одинаковые пароли, то и хеши у них будут одинаковые. Для того чтобы избавиться
    // от этих слабых мест в защите, достаточно добавить к паролю случайную последовательность байтов. Точнее делать
    // это каждый раз, когда пароль изменяется. Тогда, конечно, надо предусмотреть в таблице users поля для
    // хранения хеша и salt. У нас хэш будет храниться в поле password.
    public static String ComputeHash(String password, String salt)
    {
        MessageDigest digest;
        byte[] w = Hex.decode(new String(Hex.encode(password.getBytes())) + salt);
        try {
            digest = MessageDigest.getInstance("SHA-256");
        }
        catch (Exception ex)
        {
            return "";
        }
        return new String(Hex.encode(digest.digest(w)));
    }

}
