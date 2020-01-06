package states;

import Render.AlienDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;



public class LoginState extends State {

    private Texture background;
    private Stage stage;
    private TextField txtUsername;
    private TextField txtPassword;
    private Button loginBtn;
    private Button registerStateBtn;
    private Skin skin = new Skin(Gdx.files.internal("flat-earth-ui.json"));

    public LoginState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        createCanvas();
        background = new Texture("sea.jpg");
    }

    private void createCanvas(){
        skin.getFont("font").getData().setScale(1.33F);

        txtUsername = new TextField("", skin);
        txtUsername.setSize(250, 40);
        txtUsername.setPosition(AlienDemo.WIDTH /2F - (txtUsername.getWidth()/2f), AlienDemo.HEIGHT/2F - (txtUsername.getHeight()/2f));
        txtUsername.setMessageText("Username");
        stage.addActor(txtUsername);
        txtPassword = new TextField("", skin);
        txtPassword.setSize(250, 40);
        txtPassword.setPosition(AlienDemo.WIDTH /2F - (txtPassword.getWidth()/2f), AlienDemo.HEIGHT/2F - (txtPassword.getHeight()/2f) -60);
        txtPassword.setMessageText("Password");
        txtPassword.setPasswordCharacter('*');
        txtPassword.setPasswordMode(true);
        stage.addActor(txtPassword);
        loginBtn = new TextButton("Login", skin);
        loginBtn.setPosition(AlienDemo.WIDTH /2F - (loginBtn.getWidth()/2f), AlienDemo.HEIGHT/2F - (loginBtn.getHeight()/2f) - 120);
        loginBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("test");

            }
        });
        stage.addActor(loginBtn);
        registerStateBtn = new TextButton("Register", skin);
        registerStateBtn.setPosition(AlienDemo.WIDTH /2F - (registerStateBtn.getWidth()/2f), AlienDemo.HEIGHT/2F - (registerStateBtn.getHeight()/2f) - 180);
        registerStateBtn.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        gsm.push(new RegisterState(gsm));

            }
        });
        stage.addActor(registerStateBtn);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, AlienDemo.WIDTH, AlienDemo.HEIGHT);
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}
