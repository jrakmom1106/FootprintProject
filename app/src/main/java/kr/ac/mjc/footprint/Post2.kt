package kr.ac.mjc.footprint

import java.util.*

class Post2 (
    //var imageUrl:String="", // 업로드한 이미지의 경로
    var textTitleEt:String="", //입력한 문구 저장 변수
    var contentEt:String="",
    var incomeEt:String="",
    var expEt: String = "",
    var memoEt: String = "",
    var userId:String="", //포스트를 업로드한 사용자의 아이디
    var id:String?=null,
    var uploadDate: Date = Date() //업로드한 시간. > sns 에서 중요하고 정렬이 되게 하기 위함
        ){

}

/*{
    constructor(){}


    constructor(/*imageUrl:String,*/text:String,text1:String,text2:String,text3:String,text4:String,userId:String,/*id:String*/){
        //업로드된 시간을
        //this.imageUrl=imageUrl
        this.textTitleEt=text
        this.contentEt=text1
        this.incomeEt=text2
        this.expEt = text3
        this.memoEt = text4
        this.userId=userId
        this.id = id
        uploadDate= Date() //생성자를 이용함.



    }

    //맴버변수 선언
    var imageUrl:String="" // 업로드한 이미지의 경로
    var textTitleEt:String="" //입력한 문구 저장 변수
    var contentEt:String=""
    var incomeEt:String=""
    var expEt: String = ""
    var memoEt: String = ""
    var userId:String="" //포스트를 업로드한 사용자의 아이디
    var id:String?=null
    var uploadDate: Date = Date() //업로드한 시간. > sns 에서 중요하고 정렬이 되게 하기 위함
}*/