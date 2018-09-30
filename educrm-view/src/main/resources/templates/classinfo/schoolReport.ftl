<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <title></title>
    <link href="/css/style.default.css" rel="stylesheet">
    <style>
        body,*{
            background-color: #fff;
        }
        table{
            margin-bottom: 15px;
        }
        .resultstable h3{
            line-height: 40px;
        }
        .resultstable h3 span{
            font-size: 16px;
        }
        .resultstable{
            padding: 15px;
            width: 100%;
        }
        .resultstable table{
            width: 100%;
        }
        .resultstable table th{
            font-weight: 500;
        }
        .resultstable table th,table td{
            border:1px solid #cacaca;
            padding: 5px 10px;;
        }
        .resultstable .comments{

            height: 60px;
            vertical-align: top;
        }
        .lastcomments{
            height: 80px;
            vertical-align: top;
        }

    </style>
</head>
<body>
<div class="resultstable">
    <h3 class="text-center"><#--20__—20__学年__学年期中/期末考试成绩单-->${(student.map.title?string)!}考试成绩单
        <br>
        <span>The transcript of sthudent about ${(student.map.title?string)!}</span>
    </h3>
    <table>
        <thead>
        <tr>
            <th>姓名Name：${(student.caption)!}</th>
            <th>班级Class：${(classinfo.caption)!}</th>
            <th>年级Grade：${(classinfo.gradeEntity.caption)!}</th>
            <th>学部Apartment：${(classinfo.divisionEntity.caption)!}</th>
            <th colspan="2">备注：</th>
        </tr>
        </thead>
    </table>
    <table class="table-re">
        <thead>

        <tr>
            <th>课程名称Subject</th>
            <#if student.map.testplans??>
                <#list student.map.testplans as t>
                    <th>${(t.caption)!}111</th>
                </#list>
            </#if>
            <th>学期综合评定等级Evaluation</th>
            <th>任课教师评语Remark</th>
        </tr>
        </thead>
        <tbody>
        <#if student.map.scoresList ??>
            <#list student.map.scoresList as h>
                <tr>
                    <td>${(h.map.courseEntity.caption)!}</td>
                    <#if h.map.sysDictValues??>
                        <#list h.map.sysDictValues as d>
                        <td>
                            <#--map.studentTestPlansScores.-->
                            ${(d.scores)!}
                        </td>
                        </#list>
                    </#if>
                    <td>A级</td>
                    <td>
                        <p class="comments text-left">${(h.map.teacherComments.content)!}</p>
                        <p class="text-left">  教师签字Signature:</p>
                    </td>
                </tr>
            </#list>
        </#if>

        <tr>
            <td colspan="6" align="left" class="lastcomments">班主任评语 Home remark：${(student.map.headmasterComments.content)!}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>

<script src="/js/jquery-1.11.1.min.js"></script>
</html>