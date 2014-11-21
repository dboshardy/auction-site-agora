class FlagsController < ApplicationController
  before_action :set_flag, only: [:show, :create, :new, :edit, :update, :destroy]
  publishes_to :flag

  # GET /flags
  # GET /flags.json
  def index
  end

  # GET /flags/1
  # GET /flags/1.json
  def show
  end

  # GET /flags/new
  def new
  end

  # GET /flags/1/edit
  def edit
  end

  # POST /flags
  # POST /flags.json
  def create
    flag = Flag.new(flag_params)

    id = SecureRandom.uuid.to_s

    flag_info = {:id => id, :type => "create", 
      :flag_id => params[:id],
      :flag_type => flag.flag_type,
      :flag_desc => flag.flag_description,
      :user_id => session[:user_id]     
    }

    publish :flag, JSON.generate(flag_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Flag created!"
    else
      @status = "Flag could not be created"
    end

    render 'confirm'
        # @flag = Flag.new(flag_params)

    # respond_to do |format|
    #   if @flag.save
    #     format.html { redirect_to @flag, notice: 'Flag was successfully created.' }
    #     format.json { render :show, status: :created, location: @flag }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @flag.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # PATCH/PUT /flags/1
  # PATCH/PUT /flags/1.json
  def update
    flag = Flag.new(flag_params)

    id = SecureRandom.uuid.to_s

    flag_info = {:id => id, :type => "update", 
      :flag_id => params[:id],
      :flag_type => flag.flag_type,
      :flag_desc => flag.flag_description,
      :user_id => session[:user_id]     
    }

    publish :flag, JSON.generate(flag_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Flag updated!"
    else
      @status = "Flag could not be updated"
    end

    render 'confirm'    
    # respond_to do |format|
    #   if @flag.update(flag_params)
    #     format.html { redirect_to @flag, notice: 'Flag was successfully updated.' }
    #     format.json { render :show, status: :ok, location: @flag }
    #   else
    #     format.html { render :edit }
    #     format.json { render json: @flag.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # DELETE /flags/1
  # DELETE /flags/1.json
  def destroy
    id = SecureRandom.uuid.to_s

    flag_info = {:id => id, :type => "delete", 
      :flag_id => params[:id]
   
    }

    publish :flag, JSON.generate(flag_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Flag deleted!"
    else
      @status = "Flag could not be deleted"
    end

    render 'confirm'     # @flag.destroy
    # respond_to do |format|
    #   format.html { redirect_to flags_url, notice: 'Flag was successfully destroyed.' }
    #   format.json { head :no_content }
    # end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_flag
      redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def flag_params
      params.require(:flag).permit(:flag_type, :flagging_user, :auction_id)
    end
end
