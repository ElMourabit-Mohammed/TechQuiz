package com.example.techquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    String[] questions,answers,opt;
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    public static String languagename;
    TextView countDownTimerView;
    private CountDownTimer countDownTimer;
    private long countDownInMs=30000;
    private boolean timerRunning;
    User currentuser;
    AlertDialog.Builder builder;
    //QUIZ quetions---------------------------------
    //javascript------------------------------------
    String questionsJS[] = {
            "Inside which HTML element do we put the JavaScript?",
            "Which of the following syntax is correct to refer an external script called “formValidation.js”?",
            "JavaScript is interpreted by _________",
            "What JavaScript keyword declares a variable?",
            "How can you add a comment in a JavaScript?",
            "How do you create a function in JavaScript?",
            "How do you write \"Hello World\" in an alert box?",
            "Which operator returns true if the two compared values are not equal?",
            "Which property references the DOM object that dispatched an event?",
            "Which method converts JSON data to a JavaScript object?"
    };
    String answersJS[] = {"<script>","<script src=“formValidation.js”>","Client","var","//This is a comment","function myFunction()","alert(\"Hello World\");","!==","target","JSON.parse()"};
    String optJS[] = {
            "<script>","<javascript>","<scripting>","<js>",
            "<script href=“formValidation.js”>","<script source=“formValidation.js”>","<script name=“formValidation.js”>","<script src=“formValidation.js”>",
            "Client","Server","Object","None of the above",
            "var","variable","Var","create",
            "//This is a comment","'This is a comment'","<!--This is a comment-->","None of the above",
            "function:myFunction()","function myFunction()","function = myFunction()","None of the above",
            "alertBox(\"Hello World\");","msgBox(\"Hello World\");","msg(\"Hello World\");","alert(\"Hello World\");",
            "<>","~","==!","!==",
            "self","object","target","source",
            "JSON.fromString()","JSON.parse()","JSON.toObject()","JSON.stringify()"
    };
    //java------------------------------------
    String questionsJava[] = {
            "Given the string \"strawberries\" saved in a variable called fruit, what would fruit.substring(2, 5) return?",
            "How can you achieve runtime polymorphism in Java?",
            "The runtime system starts your program by calling which function first?",
            "What method can be used to create a new instance of an object?",
            "Which type of variable keeps a constant value once it is assigned?",
            "Which keyword lets you call the constructor of a parent class?",
            "Which keyword lets you use an interface?",
            "Declare a variable that holds the first four digits of Π",
            "Which is not a java keyword",
            "Which operator would you use to find the remainder after division?"
    };
    String answersJava[] = {"raw","method overriding","main","constructor","final","super","implements;","double pi = 3.141;","unsigned","%"};
    String optJava[] = {
            "rawb","raw","awb","traw",
            "method overloading","method overrunning","method overriding","method calling",
            "print","iterative","hello","main",
            "another instance","field","constructor","private method",
            "non-static","static","final","private",
            "parent","super","this","new",
            "extends","implements","inherits","import",
            "int pi = 3.141;","decimal pi = 3.141;","double pi = 3.141;","float pi = 3.141;",
            "finally","native","interface","unsigned",
            "%","//","/","DIV"
    };
    //C------------------------------------
    String questionsC[] = {
            "What is the name for calling a function inside the same function?",
            "Which is the smallest program to compile and run without errors?",
            "What is optional in a function declaration?",
            "In which segment does dynamic memory allocation takes place?",
            "Which function do you use to deallocate memory?",
            "When is memory for a variable allocated?",
            "What is an alternative way to write the expression (*x).y?",
            "Directives are translated by the _______",
            "By default, C Functions are what type of functions?",
            "Which operator is used to access the address of a variable?"
    };
    String answersC[] = {"recursion ","int main() {return 0;}","parameter names","heap","free()","during the declaration of the variable","x->y;","Pre-processor","library","&"};
    String optC[] = {
            "recursion","subfunction","inner call","infinite loop",
            "main()","int main() {return 0;}","main() { }","main() { ; }",
            "data type of parameters","return type of function","parameter names","number of parameters",
            "BSS Segment","stack","heap","data segment",
            "dalloc()","dealloc()","release()","free()",
            "during the assigment of the variable","during the initialization of the variable","during the initialization of the variable","during the definition of the variable",
            "There is no equivalent","x->y","*x->y","y->x",
            "Pre-processor","Compiler","Linker","Editor",
            "global","static","library","system",
            "%","**","*","&"
    };
    //Cpp------------------------------------
    String questionsCpp[] = {
            "Which statement is true?",
            "What is a variable of type double?",
            "Which one of the following is a keyword?",
            "The modulus operator uses ___ character.",
            "Every variable should be separated by ____ separator.",
            "Signed, unsigned, long and short are some of the _____",
            "Float and double are related to ____ data type.",
            "Variable names must begin with ___",
            "Integer values are stored in ___ bit format in binary form.",
            "Which operator requires one operand?"
    };
    String answersCpp[] = {"C++ supports multiple inheritance ","a floating point number","Switch","%","Comma","Modifiers","Floating","Letter","16","Unary"};
    String optCpp[] = {
            "Only classes can have member variables and methods","C++ supports multiple inheritance","C++ supports only single inheritance","Only structs can inherit",
            "a 2-tuple","an integer number","a floating point number","a string with more than 255 characters",
            "Size","Key","Jump","Switch",
            "+","*","/","%",
            "Dot","Colon","Comma","Semicolon",
            "Void","Data","Derived data","Modifiers",
            "Void","Floating","Fixed","Integral",
            "#","$","Number","Letter",
            "8","16","32","64",
            "Unary","Binary","Ternary","Both (a) and (b)"
    };
    //Python------------------------------------
    String questionsPython[] = {
            "What built-in list method would you use to remove items from a list?",
            "What is the term used to describe items that may be passed into a function?",
            "Which collection type is used to associate values with unique keys?",
            "What is the algorithmic paradigm of quick sort?",
            "What symbol(s) do you use to assess equality between two elements?",
            "What file is imported to use dates in python?",
            "What does the // operator in Python 3 allow you to do?",
            "What is the correct syntax for defining a class called Game?",
            "What built-in Python data type can be used as a hash table?",
            "Which choice is an immutable data type?"
    };
    String answersPython[] = {".pop() method","arguments","dictionary","divide and conquer","==","datetime","Perform integer division","class Game: pass","dictionary","string"};
    String optPython[] = {
            ".delete() method","pop(my_list)","del(my_list)",".pop() method",
            "arguments","paradigms","attributes","decorators",
            "slot","dictionary","queue","sorted list",
            "backtracking","dynamic programming","decrease and conquer","divide and conquer",
            "&&","=","==","||",
            "datetime","dateday","daytime","timedate",
            "Perform integer division","Perform operations on exponents","Find the remainder of a division operation","Perform floating point division",
            "def Game(): pass","def Game: pass","class Game: pass","class Game(): pass",
            "set","list","tuple","dictionary",
            "dictionnary","list","set","string"
    };
    //-------------------------------------------------------------------------------------------------
    //HTML QUESTIONS
    String questionsHTML[] = {
            "HTML est considéré comme ______ ?",
            "HTML utilise des ______?",
            "Choisissez la balise HTML correcte pour un grand titre.",
            "Quelle balise est utilisée pour lister les éléments avec des puces?",
            "La balise Head est utilisée pour?",
            "Lorsque nous écrivons <img src = \"img.png\">, qu’implique « img.png »?",
            "Lequel des attributs est obligatoire dans la balise <img>?",
            "Quel élément a des propriétés très similaires à l’élément DIV?",
            "Quelle est la forme complète du HTML?",
            "Les pages Web HTML peuvent être lues et rendues par le _________."
    };
    String answersHTML[] = {"Langage de balisage","Balises fixes définis par le langage","H1","<ul>…</ul>","Tout les réponses sont vrais"
            ,"valeur","src","l’élément span","HyperText Markup Language","Navigateur Web"};
    String optHTML[] = {
            "Langage de programmation","Langage POO","Langage de haut niveau","Langage de balisage",
            "Balises définis par l’utilisateur","Balises prédéfinis","Balises fixes définis par le langage","Balises uniquement pour les liens",
            "H1","Heading","Head","H6",
            "<puce>…</puce>","<list>…</list>","<ul>…</ul>","<ol>…</ol>",
            "Écrire des styles CSS","Écrire du Javascript","Inclure des fichiers CSS et JS","Tout les réponses sont vrais",
            "élément","attribut","valeur","opérateur",
            "src","href","id","alt",
            "l’élément strong","l’élément span","l’élément table","Tout les réponses sont vrais",
            "HyperText Markup Language","Hyper Teach Markup Language","Hyper Tech Markup Language","Aucune de ces réponses n’est vraie.",
            "Compilateur","Serveur","Navigateur Web","Interpréteur"
    };

    //-------------------------------------------------------------------------------------------------
    //CSS QUESTIONS
    String questionsCSS[] = {
            "Si nous souhaitons définir le style d’un seule élément, quel sélecteur css utiliserons-nous?",
            "La balise HTML qui spécifie un style CSS intégré dans un élément est appelée ____?",
            "Si nous souhaitons placer du texte autour d’une image, quelle propriété CSS nous allons utiliser?",
            "CSS est un acronyme pour _____",
            "Comment écrire un commentaire en CSS?",
            "Quelle propriété CSS est utilisée pour contrôler la taille du texte d’un élément?",
            "La valeur par défaut de l’attribut « position » est _________.",
            "Comment rendre tous les paragraphes en «ROUGE» ?",
            "Quelle est la syntaxe correcte du code CSS suivant?",
            "En css, qu’est-ce que h1 peut être appelé comme ______"
    };
    String answersCSS[] = {"id","Style","float","Cascading Style Sheet","/* un commentaire */"
            ,"font-size","relative","p {color: red;}","Body {color: Black}","Sélecteur"};
    String optCSS[] = {
            "id","text","class","name",
            "Design","Style","Modify","Define",
            "push","float","align","wrap",
            "Cascading Style Sheet","Costume Style Sheet","Cascading System Style","Aucune de ces réponses n’est vraie.",
            "<‘ un commentaire ‘>","/ un commentaire /","// un commentaire //","/* un commentaire */",
            "font-style","text-size","font-size","text-style",
            "fixed","absolute","inherit","relative",
            "p.all {color: red;}","p.all {color: #AA0000;}","all.p {color: #0000FF;}","p {color: red;}",
            "Body:color=black","{body;color:black}","{body:color=black(body}","Body {color: Black}",
            "Sélecteur","Attribut","Valeur","Label"
    };

    //-------------------------------------------------------------------------------------------------
    //SQL QUESTIONS
    String questionsSQL[] = {
            "MySQL est un système de gestion de base de données _____?",
            "À quoi correspondent les données d’une base de données MySQL?",
            "Quel mot clé est utilisé pour créer une base de données?",
            "Quelle instruction apporte des modifications aux attributs de la base de données?",
            "Quel est le format par défaut pour le type de données « Date »?",
            "Est-ce que « Datetime » et « Timestamp » sont de même type de données?",
            "Quelle commande est utilisée pour supprimer une table existante?",
            "Duplication de la clé primaire est-elle autorisée dans SQL?",
            "Quels sont les types de données valides dans Mysql?",
            "Quelle clause est utilisée pour trier les lignes du résultat par une ou plusieurs colonnes ?"
    };
    String answersSQL[] = {"Relationnel","Tables","CREATE","ALTER","YYYY-MM-DD"
            ,"Aucune de ces réponses","DROP TABLE","Non","Tout les réponses sont vrais","ORDER BY"};
    String optSQL[] = {
            "Orienté objet","Hiérarchique","Relationnel","Réseau",
            "Objets","Tables","Réseaux","Systèmes de fichiers",
            "CREATE","SET","SETUP","LINK",
            "CHANGE","ALTER","ALTERNATE","UPDATE",
            "YYYY-MM-DD","MM-YYYY-DD","DD-MM-YYYY","Aucun de ces réponses",
            "Oui","Non","Dépend de SGBD","Aucune de ces réponses",
            "DROP TABLE","DELETE","A et B","Aucun de ces réponses",
            "Oui","Non","Dépend de SGBD","Aucun de ces réponses",
            "Numeric","Temporary","Text","Tout les réponses sont vrais",
            "HAVING","FROM","ORDER BY","WHERE"
    };

    //-------------------------------------------------------------------------------------------------
    //PHP QUESTIONS
    String questionsPHP[] = {
            "Que signifie PHP?",
            "Un script PHP devrait commencer par ___ ?",
            "Laquelle parmi les instructions php suivantes va stocker 55 dans la variable nbr?",
            "Laquelle des fonctions suivantes trie un tableau dans l’ordre inverse?",
            "Laquelle des fonctions suivantes vérifie si une valeur spécifique existe dans un tableau?",
            "A quoi sert la fonction strpos()?",
            "Lequel des éléments suivants est utilisé pour déclarer une constante?",
            "Lequel des éléments suivants est utilisé pour supprimer un cookie?",
            "La fonction __sleep() est appelée lorsque vous ______?",
            "PHP ne prend pas en charge _______?"
    };
    String answersPHP[] = {"Personal Home Page","<?php","$nbr= 55;","rsort()","in_array()"
            ,"Rechercher un caractère dans une chaîne","define","La fonction setcookie()","sérialiser un objet","Héritage multiple"};
    String optPHP[] = {
            "Personal Home Page","Page Home Personal","Pretext Hypertext Processor","Preprocessor Home Page",
            "<php","<?php","<?","<php?",
            "int $nbr= 55;","int nbr = 55;","$nbr= 55;","55= $nbr;",
            "rsort()","shuffle()","reset()","sort()",
            "krsort()","key()","in_array()","extract()",
            "Trouver le dernier caractère d’une chaîne","Rechercher un caractère dans une chaîne","Les deux A et B sont vrais.","Localiser la position du premier caractère d’une chaîne",
            "const","constant","define","def",
            "La fonction setcookie()","La variable $_COOKIE","La fonction isset()","Aucune de ces réponses n’est vraie.",
            "sérialiser un objet","désérialiser un objet","Partager un objet","Choisissez un objet",
            "HTML","JavaScript","Héritage multiple","Tout les réponses sont vrais"
    };

    //-------------------------------------------------------------------------------------------------
    //NET QUESTIONS
    String questionsNET[] = {
            "CLR signifie __________ ?",
            "CLR est responsable de __________ ?",
            "Quels sont les types de JIT?",
            "Garbage collector (GC) comprend _______ générations.",
            "Quel outil est utilisé pour voir le code IL?",
            "Lequel des types suivants peut contenir une valeur de n’importe quelle taille?",
            "IL en .Net signifie ________?",
            "Lequel des éléments suivants est la racine de la hiérarchie de toutes les types .NET?",
            "Laquelle des méthodes suivantes demande au garbage-collector de ne pas appeler finalize?",
            "Le code qui s’exécute sous CLR s’appelle _______?"
    };
    String answersNET[] = {"Common Language Runtime","Tout les réponses sont vrais","Tout les réponses sont vrais","Trois","IDASM.EXE"
            ,"Intermediate Language","BigInteger","System.Object","GC.SuppressFinalize()","Code managé"};
    String optNET[] = {
            "Common Local Runtime","Common Language Runtime","Common Language Realtime","Common Local Realtime",
            "Garbage-collector","Sécurité d’accès au code","Vérification du code","Tout les réponses sont vrais",
            "Pre-JIT","Econo-JT","Normal-JIT","Tout les réponses sont vrais",
            "Un","Deux","Trois","Cinq",
            "Util.exe","IL.exe","GACUtil.exe","IDASM.EXE",
            "Intermediate Language","International Language","Interoperate Language","Intermediate Local",
            "Int32","Double","Long","BigInteger",
            "System.Object","System.Type","System.Base","System.Root",
            "GC.Collect(int)","GC.Dispose()","GC.Cancel()","GC.SuppressFinalize()",
            "Code non managé","Code managé","Code COM","Code PIN"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        countDownTimerView = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        currentuser = (User) intent.getSerializableExtra("currentuser");
        languagename=intent.getStringExtra("LanguageName");
        textView.setText(languagename);

        // this the name from the activity main
        /*TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals("")) {
            textView.setText("Hello User");
        }
        else
        textView.setText("Hello " + name);*/
        startStop();
        switch (languagename.toString()){
            case "JavaScript":
                questions=questionsJS;
                answers=answersJS;
                opt=optJS;
                break;
            case "Java":
                questions=questionsJava;
                answers=answersJava;
                opt=optJava;
                break;
            case "C":
                questions=questionsC;
                answers=answersC;
                opt=optC;
                break;
            case "C++":
                questions=questionsCpp;
                answers=answersCpp;
                opt=optCpp;
                break;
            case "Python":
                questions=questionsPython;
                answers=answersPython;
                opt=optPython;
                break;
            case "CSS":
                questions=questionsCSS;
                answers=answersCSS;
                opt=optCSS;
                break;
            case "HTML":
                questions=questionsHTML;
                answers=answersHTML;
                opt=optHTML;
                break;
            case "PHP":
                questions=questionsPHP;
                answers=answersPHP;
                opt=optPHP;
                break;
            case "SQL":
                questions=questionsSQL;
                answers=answersSQL;
                opt=optSQL;
                break;
            case ".Net":
                questions=questionsNET;
                answers=answersNET;
                opt=optNET;
                break;
        }



        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                startStop();
                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                    countDownInMs=30000;
                    startStop();
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    in.putExtra("currentuser", currentuser);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });
        builder = new AlertDialog.Builder(this);
        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
                builder.setMessage("Do you want to quit the quiz")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(getApplicationContext(),Profile.class);
                                intent.putExtra("currentuser",currentuser);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                startStop();
                            }
                        })
                        .show();

            }
        });
    }

    public void startStop(){
        if(timerRunning){
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(countDownInMs,1000) {
            @Override
            public void onTick(long l) {
                countDownInMs=l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "No reponse", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                    String ansText = uans.getText().toString();

                    if(ansText.equals(answers[flag])) {
                        correct++;
                        Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        wrong++;
                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                flag++;
                startStop();
                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    in.putExtra("currentuser", currentuser);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(in);
                    return;
                }
                countDownInMs=30000;
                startStop();
                radio_g.clearCheck();
            }
        }.start();
        timerRunning=true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning=false;
    }

    public void updateTimer(){
        int minutes = (int) countDownInMs/60000;
        int seconds = (int) countDownInMs % 60000/1000;
        String timeLeftText;
        timeLeftText = "0" + minutes;
        timeLeftText += ":";
        if(seconds<10) timeLeftText += "0";
        timeLeftText+=seconds;
        countDownTimerView.setText(timeLeftText);
    }
    @Override
    public void onBackPressed(){
        startStop();
        builder.setMessage("Do you want to quit the quiz")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(getApplicationContext(),Profile.class);
                        intent.putExtra("currentuser",currentuser);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        startStop();
                    }
                })
                .show();
    }
}