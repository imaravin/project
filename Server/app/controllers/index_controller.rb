class IndexController < ApplicationController

    def signup
      email=params[:email]
      name=params[:name]
      password=params[:password]
      u=User.new
      u.email=email
      u.name=name
      u.password=password;
      u.save
      render :json => "success"

    end


      def signin
        text="fail"
        name=params[:name]
        password=params[:password]
        u=User.find_by(name:name)
        if(u != nil && u.password==password)
         text = "success"
        end

      render :json => text
      end

  def sendmail

        text = params[:text]
        mail = params[:email]
        RestClient.post "https://api:key-6f9d977ac6efbee783f97373bba38760"\
        "@api.mailgun.net/v3/aravinth.me/messages",
        :from => "aravinththangasami@gmail.com",
        :to => mail,
        :subject => "Enter the code ,
        :text => text
        render :json => 'success'
  end

   


end
