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
      CodeMailer.keymail.deliver
      render :json => "success"
     end
   


end
